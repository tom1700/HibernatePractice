define(['Vue', 'text!./Client.html', ],
function(Vue, ClientTemplate) {
const api = {
        supplier: {
            get: {
                all: '/api/v1/suppliers'
            },
            post: '/api/v1/supplier'
        },
        product: {
            get: {
                all: '/api/v1/products'
            },
            post: '/api/v1/product'
        },
        category: {
            get: {
                all: '/api/v1/categories'
            },
            post: '/api/v1/category'
        },
        transaction: {
            get: {
                all: '/api/v1/transactions'
            },
            post: '/api/v1/transaction'
        }
    };

    return Vue.component('Client', {
        template: ClientTemplate,
        data: function() {
            return {
                customerForm: {
                    city: '',
                    zipCode: '',
                    street: '',
                    companyName: ''
                },
                productList: [],
                cart: {}
            }
        },
        methods: {
            getAll: function(objectName) {
                fetch(api[objectName].get.all, {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "GET",
                }).then(
                    (res) => res.json()
                ).then((data) => {
                    this[objectName+"List"] = data.map(obj => JSON.parse(obj));
                });
            },
            addToCart: function(product) {
                this.cart[product.id] = this.cart[product.id] || { product: product, amount: 0 };
                this.cart[product.id].amount++;
                this.cart = Object.assign({}, this.cart);
            },
            buy: function(ev) {
                ev.preventDefault();
                const body = {
                    customer: this.customerForm,
                    products: this.cart
                };

                fetch(api.transaction.post, {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "POST",
                    body: JSON.stringify(body)
                }).then((res) => console.log(res));
            }
        },
        created: function() {
            this.getAll("product");
        }
    });
});