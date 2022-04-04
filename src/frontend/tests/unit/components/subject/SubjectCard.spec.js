import {shallowMount} from "@vue/test-utils";
import SubjectCard from "@/components/subject/SubjectCard";

describe("SubjectCard", () => {
    test("renders correct text", async () => {
        const subject = {
            queueActive : true,
            subjectName : "Test11",
            subjectCode: "IdattTEst"
        };
        const wrapper = shallowMount(SubjectCard, {
            props: {
                subject
            }
        });
        await expect(wrapper.find('[data-test="Test1"]').text()).toBe("IdattTEst")
        await expect(wrapper.find('[data-test="Test"]').text()).toBe("IdattTEst Test11")
    });
})