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
      fetch('/products').then((response) => {
        if (response.ok) {
          return response.json();
        }
      }).then((data) => {
        this.items = data;
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
          this.nameField = '';
          this.priceField = '';
          this.$refs.nameInput.focus();
          this.loadProducts();
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