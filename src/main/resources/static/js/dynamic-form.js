const app = Vue.createApp({});
app.component('dynamic-form', {
  template: `
        <div class="row">
            <h3 class="text-center">{{ nameField }}</h3>
            <h3 class="text-center">{{ priceField }}</h3>
        </div>
        <div class="row">
            <div class="col-6 text-end">
                <button type="button" class="btn btn-danger .btn-lg" @click="save()">Save</button>
            </div>
        </div>
  `,
  data() {
    return {
      nameField: '',
      priceField: '',
    };
  },
  methods: {
    loadProducts() {
      fetch('/products').then((response) => {
        if (response.ok) {
          return response.json();
        }
      }).then((data) => {
        this.nameField = data[0].name + 1;
        this.priceField = data[0].price + 1;
      });
    },
    save() {
      fetch('/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: this.nameField,
          price: this.priceField
        }),
      }).then((response) => {
        if (response.ok) {
          return response.json();
        }
        else {
          throw new Error('Could not save product!');
        }
      }).catch((error) => {
        console.log(error);
      });
    },
  },
  mounted: function() {
    this.loadProducts();
  }
});
app.mount('#dynamic-form');