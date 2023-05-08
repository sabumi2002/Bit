<template>
  <div>
    <video ref="video" autoplay></video>
    <button @click="takePicture">사진 촬영</button>
    <canvas ref="canvas" style="display: none;"></canvas>
  </div>
</template>

<script>
export default {
  name: "MobileCamera",
  mounted() {
    navigator.mediaDevices.getUserMedia({ video: true })
        .then((stream) => {
          this.$refs.video.srcObject = stream;
        })
        .catch((error) => {
          console.log(error);
        });
  },
  methods: {
    takePicture() {
      const video = this.$refs.video;
      const canvas = this.$refs.canvas;
      const context = canvas.getContext('2d');

      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      context.drawImage(video, 0, 0, canvas.width, canvas.height);

      const dataURL = canvas.toDataURL('image/png');
      // dataURL을 사용하여 이미지를 업로드하거나 저장할 수 있습니다.

      console.log(dataURL);
    }
  }
}
</script>

<style scoped>

</style>