//connecting to our signaling server

let conn = new WebSocket('wss://i4a408.p.ssafy.io/socket');
//let conn = new WebSocket('ws://localhost:8080/socket');
const num = Math.floor(Math.random() * 1234567);

let remoteStream;
conn.onopen = async function() {
  console.log('Connected to the signaling server');
  console.log('num : ' + num);
  initialize();
};

conn.onmessage = function(msg) {
  console.log('Got message', msg.data);
  let content = JSON.parse(msg.data);
  let data = content.data;
  let idx = content.idx;
  let type = content.type;
  if (idx != num || type == 'callee') return;
  switch (content.event) {
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

// 접속하면 신호 보냄.
function initialize() {
  send({
    event: 'watcher',
    data: { event: 'watcher' },
    idx: num,
    type: 'callee',
  });
}

export function watch() {
  return remoteStream;
}

function handleOffer(offer) {
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
  // Setup ice handling
  peerConnection.onicecandidate = function(event) {
    if (event.candidate) {
      send({
        event: 'candidate',
        data: event.candidate,
        idx: num,
        type: 'callee',
      });
    }
  };

  peerConnection.onaddstream = function(event) {
    console.log('test');
    remoteStream = event.stream;
  };

  peerConnection.setRemoteDescription(new RTCSessionDescription(offer));
  // create and send an answer to an offer
  peerConnection.createAnswer(
    function(answer) {
      peerConnection.setLocalDescription(answer);
      send({
        event: 'answer',
        data: answer,
        idx: num,
        type: 'callee',
      });
    },
    function(error) {
      console.log(error);
      //alert('Error creating an answer');
    }
  );
}

function handleCandidate(candidate) {
  peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}

window.onunload = window.onbeforeunload = () => {
  conn.close();
  peerConnection.close();
};
