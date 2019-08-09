window.onload = function () {

  new Vue({
    el: '#app',
    data () {
      return {
        publicKey: null,
        signatureOk: false,
      }
    },
    mounted () {
      this.publicKey = JSON.parse(publicKeyJson).b64PublicKey;
      this.runCalculate();
    },
    methods: {
      runCalculate () {

        let verify = new JSEncrypt();
        verify.setPublicKey(this.publicKey);

        let word = 'theEndOfTheWorld :) ?'; // this word has generate by private key
        let signature = 'BzRtQWC3q/HN8ZmjrZmqugdVVqK42lCBO5qgOcfH+5EOEKYRfEdBk6ZLl+WT19KJmznTER7inZsLg+3ChNfp8MpqmniiAJu6An65Z7rdKxEe7SH3J23TW7jCUBb2Z1mUcl+Kf46tHt1/BOIxu3L3Oazww2yfXK7TiKYQmZJiqXOhCRdzwM0R86qf1qhYjD/AQ69QlFECAJdO1Mzwa6QNEXbDCBqSzSVT1/xWiWU7tASWPPq+X1xu6702vZjYizqYn7XgfJibzRRJ1jTafG0mha74UTU9ljub+EaEEpyxgc3zVR0POJGoFKa7P9TpwR9NYb6m0nVeTlvCi2GUSghPvw==';
        this.signatureOk = verify.verify(word, signature, CryptoJS.SHA256);
      }
    },
  })

}