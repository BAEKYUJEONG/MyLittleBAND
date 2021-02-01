<template>
    <!-- 파일 첨부 폼 -->
    <v-main>
      <v-file-input v-model="files" show-size label="File input"></v-file-input>
      <v-btn @click="upload" color="primary">Upload</v-btn>
      <p>File Name : {{ files.name }}</p>
    </v-main>
</template>
<script>
import axios from 'axios';

export default {
  name: 'App',
  components: {
  },
  data: () => ({
    files: [],
  }),
  methods: {
    async upload() {
      // 여기서 파일 업로드에 대한 요청 백으로 보냄.

      var fd = new FormData();
      fd.append('files', this.files)
      // 아래 요청은 파일 첨부에 대한 예시.
      await axios.post('http://localhost:8080/upload',
            fd, {
              headers: {
                'Content-Type': 'multipart/form-data'
              }
            }
          ).then( response => {
            console.log('성공');
            console.log(response.data)
          })
          .catch(function () {
            console.log('실패');
          });
    }
  
  }
};
</script>