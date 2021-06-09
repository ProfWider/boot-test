import DynamicForm from './dynamic-form.js';

const app = Vue.createApp({});
app.component('dynamic-form', DynamicForm);
app.mount('#app');