const app = Vue.createApp({});
app.component('dynamic-form', {
  template: `
    <div>
      <input v-model="nameField" placeholder="Name" ref="nameInput">
      <input v-model="priceField" placeholder="Price" @keyup.enter="save()">
      <button type="button" @click="save()">Save</button>
    </div>
    <div>
      <h3>Here are all products:</h3>
        <table>
          <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
          </thead>
          <tbody>
          <tr v-if="items.length === 0">
            <td colspan="2">No products yet</td>
          </tr>
          <tr v-for="product in items">
            <td>{{product.name}}</td>
            <td>{{product.price}}</td>
          </tr>
          <tr>
            <td>{{ nameField }}</td>
            <td>{{ priceField }}</td>
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
      axios
        .get('/products')
        .then(response => (this.items = response.data))
    },
    save() {
      axios
        .post('/products', {
          name: this.nameField,
          price: this.priceField
        })
        .then((response) => {
          this.nameField = '';
          this.priceField = '';
          this.$refs.nameInput.focus();
          this.loadProducts();
        }, (error) => {
          console.log('Could not save product!');
        });
    },
  },
  mounted: function() {
    this.loadProducts();
  }
});
app.mount('#dynamic-form');