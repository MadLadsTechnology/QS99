import { shallowMount } from "@vue/test-utils";
import BaseInput from "@/components/BaseComponents/BaseInput";

describe('BaseInput', () => {
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
  });
});
