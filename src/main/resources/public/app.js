requirejs.config({
    baseUrl: 'lib',
    paths: {
        Vue: 'vue',
        VueRouter: 'vue-router'
    }
});

requirejs(['Vue', '/screens/App/App.js'], function(Vue, App) {
    new Vue({
      el: '#app-container',
      App: App
    });
});