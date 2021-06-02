const app = Vue.createApp({});
app.component('dynamic-form', {
  template: `
        <div class="row">
            <input v-model="nameField" placeholder="Name">
            <input v-model="priceField" placeholder="Price">
            <p>{{ nameField }}</p>
            <p>{{ priceField }}</p>
        </div>
        <div class="row">
            <div class="col-6 text-end">
                <button type="button" class="btn btn-danger .btn-lg" @click="save()">Save</button>
            </div>
        </div>
        <div class="row">
            <h2>Here are all products</h2>
              <table>
                <thead>
                <tr>
                  <th>Name</th>
                  <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr v-if="this.items === []">
                  <td colspan="2">No Products Available</td>
                </tr>
                <tr v-for="product in items">
                  <td>{{product.name}}</td>
                  <td>{{product.price}}</td>
                </tr>
                </tbody>
              </table>
        </div>
  `,
  data() {
    return {
      items: [],
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
        this.items = data;
        //this.nameField = data[0].name + 1;
        //this.priceField = data[0].price + 1;
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