import { shallowMount } from "@vue/test-utils";
import BaseInput from "@/components/BaseComponents/BaseInput";

describe('BaseInput', () => {
    test('Changes modelValue on input', async () => {
        const inputValue1 = "text";
        const wrapper = shallowMount(BaseInput, {
            props: {
                label: "",
                modelValue: "",
                error: "",
            },
        });
        const input = wrapper.find('[data-testid="baseInputField"]');
        input.element.value = inputValue1;
        await input
            .trigger('input')
            .then(() => expect(wrapper.vm.modelValue).toEqual(inputValue1))
            .catch((error => console.log(error)));

        const inputValue2 = "changedText";
        input.element.value = inputValue2;
        await input
            .trigger("input")
            .then(() => expect(wrapper.vm.modelValue).toEqual(inputValue2))
            .catch((error => console.log(error)));
    });

    test('Renders label when value set', () => {
        const label = 'label'
        const wrapper = shallowMount(BaseInput, {
            props: {
                label,
                modelValue: "",
                error: "",
            },
        });

        expect(wrapper.text()).toBe(label);
    })
})