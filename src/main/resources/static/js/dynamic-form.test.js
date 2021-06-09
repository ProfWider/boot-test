import { mount } from '@vue/test-utils'
import DynamicForm from './dynamic-form.js';
global.axios = require('axios');

test('displays title', () => {
  const wrapper = mount(DynamicForm, {
    props: {
      title: 'Here are all products:'
    }
  })

  expect(wrapper.text()).toContain('Here are all products')
})