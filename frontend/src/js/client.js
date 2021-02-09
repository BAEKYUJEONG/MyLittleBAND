//connecting to our signaling server 

var conn = new WebSocket('ws://localhost:8080/socket');

//const localVideo = document.getElementById("localVideo");
//const remoteVideo = document.getElementById("remoteVideo");
let localStream;
let remoteStream;

conn.onopen = async function () {
  console.log("Connected to the signaling server");
  initialize();
  
};

conn.onmessage = function (msg) {
  console.log("Got message", msg.data);
  var content = JSON.parse(msg.data);
  var data = content.data;
  switch (content.event) {
    // when somebody wants to call us
    case "offer":
      handleOffer(data);
      break;
    case "answer":
      handleAnswer(data);
      break;
    // when a remote peer sends an ice candidate to us
    case "candidate":
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
  //var configuration = null;

  peerConnection = new RTCPeerConnection({configuration: {
    offerToReceiveAudio: true,
    offerToReceiveVideo: true
  },
  iceServers: [{ urls: 'stun:stun.l.google.com:19302' }]
});
  console.log(peerConnection);
  // Setup ice handling
  peerConnection.onicecandidate = function (event) {
    if (event.candidate) {
      send({
        event: "candidate",
        data: event.candidate
      });
    }
  };

  peerConnection.onaddstream = function(event) {
    console.log("test");
    remoteStream = event.stream;
  };
}

export async function broadcast2(){
  try {
    const stream = await navigator.mediaDevices.getUserMedia({
      audio: false,
      video: true,
    });
    console.log("Received local stream");
    //localVideo.srcObject = stream;
    localStream = stream;
    console.log(stream);

    
  } catch (e) {
    alert(`getUserMedia() error: ${e.name}`);
  }
  
  peerConnection.addStream(localStream);
  
  return localStream;
  
}

export function watch(){
  return remoteStream;
}


export function createOffer() {
  peerConnection.createOffer(function (offer) {
    send({
      event: "offer",
      data: offer
    });
    peerConnection.setLocalDescription(offer);
  }, function (error) {
    console.log(error);
    alert("Error creating an offer");
  });
}

function handleOffer(offer) {
  peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

  // create and send an answer to an offer
  peerConnection.createAnswer(function (answer) {
    peerConnection.setLocalDescription(answer);
    send({
      event: "answer",
      data: answer
    });
  }, function (error) {
    console.log(error);
    alert("Error creating an answer");
  });

}

function handleCandidate(candidate) {
  peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}

function handleAnswer(answer) {
  peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
  console.log("connection established successfully!!");
}