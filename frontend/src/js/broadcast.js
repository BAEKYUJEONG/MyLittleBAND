//connecting to our signaling server

var conn = new WebSocket('wss://i4a408.p.ssafy.io/socket');
// let conn = new WebSocket('ws://localhost:8080/socket');

//const localVideo = document.getElementById("localVideo");
//const remoteVideo = document.getElementById("remoteVideo");
let localStream;
const len = 2;
const peerConnections = {};

conn.onopen = async function() {
  console.log('Connected to the signaling server');
  //console.log(conn.id);
  initialize();
};

conn.onmessage = function(msg) {
  console.log('Got message', msg.data);

  let content = JSON.parse(msg.data);
  let data = content.data;
  let idx = content.idx;
  switch (content.event) {
    // when somebody wants to call us
    case 'answer':
      handleAnswer(data, idx);
      break;
    // when a remote peer sends an ice candidate to us
    case 'candidate':
      handleCandidate(data, idx);
      break;
    default:
      break;
  }
};

function send(message) {
  conn.send(JSON.stringify(message));
}

function initialize() {
  for (let i = 0; i < len; i++) {
    peerConnections[i] = new RTCPeerConnection({
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
  }
  // Setup ice handling
  for (let i = 0; i < len; i++) {
    peerConnections[i].onicecandidate = function(event) {
      if (event.candidate) {
        send({
          event: 'candidate',
          data: event.candidate,
        });
      }
    };
  }
}

//1:1

export async function broadcast2() {
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
    alert(`getUserMedia() error: ${e.name}`);
  }
  //peerConnection.addStream(localStream);
  for (let i = 0; i < len; i++) {
    peerConnections[i].addStream(localStream);
  }
  return localStream;
}

export function createOffer() {
  for (let i = 0; i < len; i++) {
    peerConnections[i].createOffer(
      function(offer) {
        send({
          event: 'offer',
          data: offer,
          idx: i,
        });
        peerConnections[i].setLocalDescription(offer);
      },
      function(error) {
        console.log(error);
        alert('Error creating an offer');
      }
    );
  }
}

function handleCandidate(candidate, idx) {
  peerConnections[idx].addIceCandidate(new RTCIceCandidate(candidate));
}

function handleAnswer(answer, idx) {
  console.log(idx);
  peerConnections[idx].setRemoteDescription(new RTCSessionDescription(answer));
  console.log('connection established successfully!!');
}
