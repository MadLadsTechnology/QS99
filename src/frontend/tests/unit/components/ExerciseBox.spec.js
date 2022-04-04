import { shallowMount } from "@vue/test-utils";
import ExerciseBox from "@/components/ExerciseBox.vue"

describe("ExerciseBox", () => {
    let confirmSpy;
    beforeAll(() => {
        confirmSpy = jest.spyOn(window, 'confirm');
        confirmSpy.mockImplementation(jest.fn(() => true));
    });
    afterAll(() => confirmSpy.mockRestore());
    test("Test if exercise approves", async () => {
        const subjectId = 1;
        const exercise = {};
        const student = "";
        const wrapper = shallowMount(ExerciseBox, {
            props: {
                subjectId,
                exercise,
                student
            }
        });
        await wrapper.find('[data-test="div"]').trigger("click");
        await expect(wrapper.find('[data-test="product"]'))
    });
})