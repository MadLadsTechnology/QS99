import { shallowMount } from "@vue/test-utils";
import LoginView from "@/views/LoginView.vue"

describe("LoginView", () => {
    test("check if login button is disabled", async () => {
        const subject = [];
        const wrapper = shallowMount(LoginView, {});
        wrapper.vm.email = "test¥test."
        wrapper.vm.password = "passord"

        expect(wrapper.find('[data-test="button"]').element.disabled).toBe(true);
    });
})