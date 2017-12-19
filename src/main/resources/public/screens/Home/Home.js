define(['Vue', 'text!./Home.html', ],
function(Vue, HomeTemplate) {
    return Vue.component('Home', {
        template: HomeTemplate
    });
});