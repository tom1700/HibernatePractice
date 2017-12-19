define(['Vue', 'text!./Admin.html', ],
function(Vue, AdminTemplate) {
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
        },
        customer: {
            get: {
                all: '/api/v1/customers'
            }
        }
    };

    return Vue.component('Admin', {
        template: AdminTemplate,
        data: function() {
            return {
                supplierForm: {
                    city: '',
                    zipCode: '',
                    street: '',
                    bankAccount: '',
                    companyName: ''
                },
                productForm: {
                    productName: '',
                    unitsOnStock: '',
                    supplierId: '',
                    categoryId: ''
                },
                categoryForm: {
                    name: '',
                },
                supplierList: [],
                productList: [],
                transactionList: [],
                categoryList: [],
                customerList: []
            }
        },
        methods: {
            post: function(ev, objectName) {
                ev.preventDefault();
                console.log(this[objectName+"Form"]);
                fetch(api[objectName].post, {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    method: "POST",
                    body: JSON.stringify(this[objectName+"Form"])
                }).then((res) => {
                    this.getAll(objectName);
                });
            },
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
            }
        },
        created: function() {
            this.getAll("supplier");
            this.getAll("product");
            this.getAll("transaction");
            this.getAll("category");
            this.getAll("customer");
        }
    });
});