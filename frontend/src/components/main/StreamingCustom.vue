<template>
  <!-- 동작에 따른 이벤트 처리가 가능한듯..? -->
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <video id="localVideo" autoplay playsinline></video>
      <video id="remoteVideo" autoplay playsinline></video>
      <div>
        <v-btn class="mx-3" id="startButton">Start</v-btn>
        <v-btn class="mx-3" id="callButton">Call</v-btn>
        <v-btn class="mx-3" id="hangupButton">Hang Up</v-btn>

        <v-btn class="mx-3" id="testButton">broadcast</v-btn>
      </div> </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
export default {
  mounted() {
    /*
     *  Copyright (c) 2015 The WebRTC project authors. All Rights Reserved.
     *
     *  Use of this source code is governed by a BSD-style license
     *  that can be found in the LICENSE file in the root of the source
     *  tree.
     */

    "use strict";

    const startButton = document.getElementById("startButton");
    const callButton = document.getElementById("callButton");
    const hangupButton = document.getElementById("hangupButton");

    const testButton = document.getElementById("testButton");

    callButton.disabled = true;
    hangupButton.disabled = true;
    startButton.addEventListener("click", start);
    callButton.addEventListener("click", call);
    hangupButton.addEventListener("click", hangup);
    testButton.addEventListener("click", test);

    let startTime;
    const localVideo = document.getElementById("localVideo");
    const remoteVideo = document.getElementById("remoteVideo");

    localVideo.addEventListener("loadedmetadata", function () {
      console.log(
        `Local video videoWidth: ${this.videoWidth}px,  videoHeight: ${this.videoHeight}px`
      );
    });

    remoteVideo.addEventListener("loadedmetadata", function () {
      console.log(
        `Remote video videoWidth: ${this.videoWidth}px,  videoHeight: ${this.videoHeight}px`
      );
    });

    remoteVideo.addEventListener("resize", () => {
      console.log(
        `Remote video size changed to ${remoteVideo.videoWidth}x${remoteVideo.videoHeight}`
      );
      // We'll use the first onsize callback as an indication that video has started
      // playing out.
      if (startTime) {
        const elapsedTime = window.performance.now() - startTime;
        console.log("Setup time: " + elapsedTime.toFixed(3) + "ms");
        startTime = null;
      }
    });

    let localStream;
    let pc1;
    let pc2;
    const offerOptions = {
      offerToReceiveAudio: 1,
      offerToReceiveVideo: 1,
    };

    function getName(pc) {
      return pc === pc1 ? "pc1" : "pc2";
    }

    function getOtherPc(pc) {
      return pc === pc1 ? pc2 : pc1;
    }

    async function start() {
      console.log("Requesting local stream");
      startButton.disabled = true;
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          audio: true,
          video: true,
        });
        console.log("Received local stream");
        localVideo.srcObject = stream;
        localStream = stream;
        callButton.disabled = false;
      } catch (e) {
        alert(`getUserMedia() error: ${e.name}`);
      }
    }

    async function test() {
      console.log("Requesting local stream");
      startButton.disabled = true;
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          audio: true,
          video: true,
        });
        console.log("Received local stream");
        localVideo.srcObject = stream;
        localStream = stream;
        callButton.disabled = false;
      } catch (e) {
        alert(`getUserMedia() error: ${e.name}`);
      }

      // 여기까지 정보 얻어오기.

      startTime = window.performance.now();
      const videoTracks = localStream.getVideoTracks();
      const audioTracks = localStream.getAudioTracks();
      if (videoTracks.length > 0) {
        console.log(`Using video device: ${videoTracks[0].label}`);
      }
      if (audioTracks.length > 0) {
        console.log(`Using audio device: ${audioTracks[0].label}`);
      }

      var conn = new WebSocket("ws://localhost:8080/socket");

      conn.onopen = function () {
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

      var dataChannel;
      var input = document.getElementById("messageInput");

      function initialize() {
        var configuration = {
          iceServers: [
            {
              url: "stun:stun2.1.google.com:19302",
            },
          ],
        };

        pc1 = new RTCPeerConnection(configuration);

        // Setup ice handling
        pc1.onicecandidate = function (event) {
          if (event.candidate) {
            send({
              event: "candidate",
              data: event.candidate,
            });
          }
        };

        // creating data channel
        dataChannel = pc1.createDataChannel("dataChannel", {
          reliable: true,
        });

        dataChannel.onerror = function (error) {
          console.log("Error occured on datachannel:", error);
        };

        // when we receive a message from the other peer, printing it on the console
        dataChannel.onmessage = function (event) {
          console.log("message:", event.data);
        };

        dataChannel.onclose = function () {
          console.log("data channel is closed");
        };

        pc1.ondatachannel = function (event) {
          dataChannel = event.channel;
        };
        createOffer();
        sendMessage();
      }

      function createOffer() {
        pc1.createOffer(
          function (offer) {
            send({
              event: "offer",
              data: offer,
            });
            pc1.setLocalDescription(offer);
          },
          function (error) {
            alert("Error creating an offer");
            console.log(error);
          }
        );
      }

      function handleOffer(offer) {
        pc1.setRemoteDescription(new RTCSessionDescription(offer));

        // create and send an answer to an offer
        pc1.createAnswer(
          function (answer) {
            pc1.setLocalDescription(answer);
            send({
              event: "answer",
              data: answer,
            });
          },
          function (error) {
            alert("Error creating an answer");
            console.log(error);

          }
        );
      }

      function handleCandidate(candidate) {
        pc1.addIceCandidate(new RTCIceCandidate(candidate));
      }

      function handleAnswer(answer) {
        pc1.setRemoteDescription(new RTCSessionDescription(answer));
        console.log("connection established successfully!!");
      }

      function sendMessage() {
        dataChannel.send(input.value);
        input.value = "";
      }
    }

    async function call() {
      callButton.disabled = true;
      hangupButton.disabled = false;
      console.log("Starting call");
      startTime = window.performance.now();
      const videoTracks = localStream.getVideoTracks();
      const audioTracks = localStream.getAudioTracks();
      if (videoTracks.length > 0) {
        console.log(`Using video device: ${videoTracks[0].label}`);
      }
      if (audioTracks.length > 0) {
        console.log(`Using audio device: ${audioTracks[0].label}`);
      }
      const configuration = {
        iceServers: [
          {
            url: "stun:stun2.1.google.com:19302",
          },
        ],
      };
      console.log("RTCPeerConnection configuration:", configuration);
      pc1 = new RTCPeerConnection(configuration); // 여기에서 서버 정보를 줘야 함.
      console.log("Created local peer connection object pc1");
      pc1.addEventListener("icecandidate", (e) => onIceCandidate(pc1, e));
      pc2 = new RTCPeerConnection(configuration);
      console.log("Created remote peer connection object pc2");
      pc2.addEventListener("icecandidate", (e) => onIceCandidate(pc2, e));
      pc1.addEventListener("iceconnectionstatechange", (e) =>
        onIceStateChange(pc1, e)
      );
      pc2.addEventListener("iceconnectionstatechange", (e) =>
        onIceStateChange(pc2, e)
      );
      pc2.addEventListener("track", gotRemoteStream);

      localStream
        .getTracks()
        .forEach((track) => pc1.addTrack(track, localStream));
      console.log("Added local stream to pc1");

      try {
        console.log("pc1 createOffer start");
        const offer = await pc1.createOffer(offerOptions);
        await onCreateOfferSuccess(offer);
      } catch (e) {
        onCreateSessionDescriptionError(e);
      }
    }

    function onCreateSessionDescriptionError(error) {
      console.log(`Failed to create session description: ${error.toString()}`);
    }

    async function onCreateOfferSuccess(desc) {
      console.log(`Offer from pc1\n${desc.sdp}`);
      console.log("pc1 setLocalDescription start");
      try {
        await pc1.setLocalDescription(desc);
        onSetLocalSuccess(pc1);
      } catch (e) {
        onSetSessionDescriptionError();
      }

      console.log("pc2 setRemoteDescription start");
      try {
        await pc2.setRemoteDescription(desc);
        onSetRemoteSuccess(pc2);
      } catch (e) {
        onSetSessionDescriptionError();
      }

      console.log("pc2 createAnswer start");
      // Since the 'remote' side has no media stream we need
      // to pass in the right constraints in order for it to
      // accept the incoming offer of audio and video.
      try {
        const answer = await pc2.createAnswer();
        await onCreateAnswerSuccess(answer);
      } catch (e) {
        onCreateSessionDescriptionError(e);
      }
    }

    function onSetLocalSuccess(pc) {
      console.log(`${getName(pc)} setLocalDescription complete`);
    }

    function onSetRemoteSuccess(pc) {
      console.log(`${getName(pc)} setRemoteDescription complete`);
    }

    function onSetSessionDescriptionError(error) {
      console.log(`Failed to set session description: ${error.toString()}`);
    }

    function gotRemoteStream(e) {
      if (remoteVideo.srcObject !== e.streams[0]) {
        remoteVideo.srcObject = e.streams[0];
        console.log("pc2 received remote stream");
      }
    }

    async function onCreateAnswerSuccess(desc) {
      console.log(`Answer from pc2:\n${desc.sdp}`);
      console.log("pc2 setLocalDescription start");
      try {
        await pc2.setLocalDescription(desc);
        onSetLocalSuccess(pc2);
      } catch (e) {
        onSetSessionDescriptionError(e);
      }
      console.log("pc1 setRemoteDescription start");
      try {
        await pc1.setRemoteDescription(desc);
        onSetRemoteSuccess(pc1);
      } catch (e) {
        onSetSessionDescriptionError(e);
      }
    }

    async function onIceCandidate(pc, event) {
      try {
        await getOtherPc(pc).addIceCandidate(event.candidate);
        onAddIceCandidateSuccess(pc);
      } catch (e) {
        onAddIceCandidateError(pc, e);
      }
      console.log(
        `${getName(pc)} ICE candidate:\n${
          event.candidate ? event.candidate.candidate : "(null)"
        }`
      );
    }

    function onAddIceCandidateSuccess(pc) {
      console.log(`${getName(pc)} addIceCandidate success`);
    }

    function onAddIceCandidateError(pc, error) {
      console.log(
        `${getName(pc)} failed to add ICE Candidate: ${error.toString()}`
      );
    }

    function onIceStateChange(pc, event) {
      if (pc) {
        console.log(`${getName(pc)} ICE state: ${pc.iceConnectionState}`);
        console.log("ICE state change event: ", event);
      }
    }

    function hangup() {
      console.log("Ending call");
      pc1.close();
      pc2.close();
      pc1 = null;
      pc2 = null;
      hangupButton.disabled = true;
      callButton.disabled = false;
    }
  },
};
</script>

<style>
</style>
