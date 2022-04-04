import { shallowMount } from "@vue/test-utils";
import ProfessorActions from "@/components/ProfessorActions.vue"

describe("ProfessorActions", () => {
    test("renders AddUserView if button is pressed", async () => {
        const subject = [];
        const wrapper = shallowMount(ProfessorActions, {
            props: {
                subject
            }
        });
        await wrapper.find('[data-test="AddUser"]').trigger("click");
        expect(wrapper.find('[data-test="AddUserObject"]').isVisible())
    });
    test("renders AddExerciseView if button is pressed", async () => {
        const subject = [];
        const wrapper = shallowMount(ProfessorActions, {
            props: {
                subject
            }
        });
        await wrapper.find('[data-test="AddExercises"]').trigger("click");
        expect(wrapper.find('[data-test="AddExercisesObject"]').isVisible())
    });
    test("renders AddMultipleUserView if button is pressed", async () => {
        const subject = [];
        const wrapper = shallowMount(ProfessorActions, {
            props: {
                subject
            }
        });
        await wrapper.find('[data-test="AddMultipleUser"]').trigger("click");
        expect(wrapper.find('[data-test="AddMultipleUsersObject"]').isVisible())
    });
    test("renders AddStudassView if button is pressed", async () => {
        const subject = [];
        const wrapper = shallowMount(ProfessorActions, {
            props: {
                subject
            }
        });
        await wrapper.find('[data-test="AddStudass"]').trigger("click");
        expect(wrapper.find('[data-test="AdStudassObject"]').isVisible())
    })
})