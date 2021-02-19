//connecting to our signaling server

//let conn = new WebSocket('wss://i4a408.p.ssafy.io/socket');
let conn = new WebSocket('ws://localhost:8080/socket');

//const localVideo = document.getElementById("localVideo");
//const remoteVideo = document.getElementById("remoteVideo");
let remoteStream;
let index;
conn.onopen = async function() {
  console.log('Connected to the signaling server');
  initialize();
};

conn.onmessage = function(msg) {
  console.log('Got message', msg.data);
  let content = JSON.parse(msg.data);
  let data = content.data;
  let idx = content.idx;
  switch (content.event) {
    // when somebody wants to call us
    case 'offer':
      handleOffer(data, idx);
      break;
    // when a remote peer sends an ice candidate to us
    case 'candidate':
      handleCandidate(data);
      break;
    default:
      break;
  }
};

function send(message) {
  conn.send(JSON.stringify(message));
}

let peerConnection;

function initialize() {
  peerConnection = new RTCPeerConnection({
    configuration: {
      offerToReceiveAudio: false,
      offerToReceiveVideo: false,
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
  //console.log(peerConnection);
  // Setup ice handling
  peerConnection.onicecandidate = function(event) {
    if (event.candidate) {
      send({
        event: 'candidate',
        data: event.candidate,
        idx: index,
      });
    }
  };

  peerConnection.onaddstream = function(event) {
    console.log('test');
    remoteStream = event.stream;
  };
}

export function watch() {
  return remoteStream;
}

function handleOffer(offer, idx) {
  peerConnection.setRemoteDescription(new RTCSessionDescription(offer));
  index = idx;
  // create and send an answer to an offer
  peerConnection.createAnswer(
    function(answer) {
      peerConnection.setLocalDescription(answer);
      send({
        event: 'answer',
        data: answer,
        idx: idx,
      });
    },
    function(error) {
      console.log(error);
      alert('Error creating an answer');
    }
  );
}

function handleCandidate(candidate) {
  peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}
