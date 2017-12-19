define([
    'Vue',
    'VueRouter',
    'text!./App.html',
    '../Home/Home.js',
    '../Client/Client.js',
    '../Admin/Admin.js'
], function(
    Vue,
    VueRouter,
    AppTemplate,
    Home,
    Client,
    Admin
) {
    Vue.use(VueRouter);

    return Vue.component('App', {
        template: AppTemplate,
        router: new VueRouter({
            routes: [
                { path: '/', component: Home },
                { path: '/client', component: Client },
                { path: '/admin', component: Admin }
            ]
        })
    });
});