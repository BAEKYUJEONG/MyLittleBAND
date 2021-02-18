//connecting to our signaling server

let conn = new WebSocket('wss://i4a408.p.ssafy.io/socket');
//let conn = new WebSocket('ws://localhost:8080/socket');

//const localVideo = document.getElementById("localVideo");
//const remoteVideo = document.getElementById("remoteVideo");
let localStream;
const peerConnections = {};

conn.onopen = async function() {
  console.log('Connected to the signaling server');
};

conn.onmessage = function(msg) {
  console.log('Got message', msg.data);

  let content = JSON.parse(msg.data);
  let data = content.data;
  let idx = content.idx;
  let type = content.type;
  if (type == 'caller') return;
  switch (content.event) {
    case 'answer':
      handleAnswer(data, idx);
      break;
    // when a remote peer sends an ice candidate to us
    case 'candidate':
      handleCandidate(data, idx);
      break;
    case 'watcher':
      handleWatcher(idx);
      break;
    case 'disconnectPeer':
      handleDisconnectPeer(idx);
      break;
    default:
      break;
  }
};

function send(message) {
  conn.send(JSON.stringify(message));
}

async function handleWatcher(idx) {
  const peerConnection = new RTCPeerConnection({
    configuration: {
      offerToReceiveAudio: true,
      offerToReceiveVideo: true,
    },
    iceServers: [
      {
        urls: 'stun:stun.l.google.com:19302',
      },
      {
        urls: 'turn:192.158.29.39:3478?transport=udp',
        credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
        username: '28224511:1379330808',
      },
      {
        urls: 'turn:192.158.29.39:3478?transport=tcp',
        credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
        username: '28224511:1379330808',
      },
    ],
  });
  peerConnections[idx] = peerConnection;

  try {
    const stream = await navigator.mediaDevices.getUserMedia({
      audio: true,
      video: true,
    });
    console.log('Received local stream');
    //localVideo.srcObject = stream;
    localStream = stream;
    //console.log(stream);
  } catch (e) {
    console.log(e);
    //alert(`getUserMedia() error: ${e.name}`);
  }

  // Setup ice handling
  peerConnection.onicecandidate = function(event) {
    if (event.candidate) {
      send({
        event: 'candidate',
        data: event.candidate,
        idx: '',
        type: 'caller',
      });
    }
  };

  peerConnection.addStream(localStream);

  peerConnection.createOffer(
    function(offer) {
      send({
        event: 'offer',
        data: offer,
        idx: idx,
        type: 'caller',
      });
      peerConnection.setLocalDescription(offer);
    },
    function(error) {
      console.log(error);
      alert('Error creating an offer');
    }
  );
}

export async function broadcast2() {
  try {
    const stream = await navigator.mediaDevices.getUserMedia({
      audio: true,
      video: true,
    });
    console.log('Received local stream');
    localStream = stream;
  } catch (e) {
    console.log(e);
    //alert(`getUserMedia() error: ${e.name}`);
  }
  return localStream;
}

function handleCandidate(candidate, idx) {
  peerConnections[idx].addIceCandidate(new RTCIceCandidate(candidate));
}

function handleAnswer(answer, idx) {
  //console.log(idx);
  peerConnections[idx].setRemoteDescription(new RTCSessionDescription(answer));
  console.log('connection established successfully!!');
}

function handleDisconnectPeer(idx) {
  peerConnections[idx].close();
  delete peerConnections[idx];
}

window.onunload = window.onbeforeunload = () => {
  conn.close();
};
