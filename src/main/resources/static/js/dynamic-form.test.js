import { mount } from '@vue/test-utils'
import DynamicForm from './dynamic-form';
global.fetch = require("node-fetch");

test('shows empty message', () => {
  const wrapper = mount(DynamicForm)

  expect(wrapper.text()).toContain('No products yet')
})
