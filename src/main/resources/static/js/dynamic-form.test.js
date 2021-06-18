import { mount } from '@vue/test-utils'
import DynamicForm from './dynamic-form.js';
global.axios = {
  get() { return Promise.resolve({ data: [] }) },
  post() { return Promise.resolve('value') }
}

test('shows empty list message', () => {
  const wrapper = mount(DynamicForm);
  expect(wrapper.text()).toContain('No products yet');
})

test('should clear name input', async () => {
  const wrapper = mount(DynamicForm);
  const nameInput = wrapper.find('input[type="text"]');
  await nameInput.setValue('Ein neues Produkt');
  await wrapper.find('button').trigger('click');
  expect(wrapper.vm.$data.nameField).toBe('');
})
