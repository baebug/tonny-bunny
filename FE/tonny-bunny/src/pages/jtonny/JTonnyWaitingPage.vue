<template>
    <div>
        <div v-if="!isFind">
            <title-text title="🐰통역을 도와줄 헬퍼를 찾는 중입니다.." />
            <large-btn
                color="light"
                font="live"
                text="찾기 취소"
                style="width: 100%"
                @click="openCancelModal" />
            <JTonnyLoading></JTonnyLoading>
        </div>
        <div v-else>
            <title-text
                title="🐰통역 가능한 헬퍼가 도착하고 있습니다..."
                text="헬퍼의 프로필을 자세히 보고 통역을 부탁한 헬퍼를 수락해주세요" />
            <large-btn
                color="light"
                font="live"
                text="찾기 취소"
                style="width: 100%"
                @click="openCancelModal" />
            <JTonnyLoading></JTonnyLoading>
            <div v-for="i in 2" :key="i">
                <helper-card @clickBtn2="openNoticeModal" />
            </div>
        </div>
        <small-btn color="light" font="live" text="찾으면" @click.prevent="찾아짐()" />

        <AlarmModal
            v-show="isOpenNotice"
            :isOpen="isOpenNotice"
            title="경고"
            type="danger"
            btnText1="취소"
            btnText2="진행"
            btnColor1="light"
            btnColor2="carrot"
            btnFontColor1="sub"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="closeModal">
            <template #content>
                수락하시면 바로 해당 헬퍼와 동시 통역이 진행됩니다. 진행하시겠습니까?
            </template>
        </AlarmModal>

        <AlarmModal
            v-show="isOpenCancel"
            :isOpen="isOpenCancel"
            title="경고"
            type="danger"
            btnText1="취소"
            btnText2="진행"
            btnColor1="light"
            btnColor2="carrot"
            btnFontColor1="sub"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="clickCancelModal">
            <template #content>
                페이지에서 벗어나면 다시 작성해야합니다.
                <br />
                계속하시겠습니까?
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    data() {
        return {
            isFind: false,
            isOpenNotice: false,
            isOpenCancel: false,
        };
    },
    components: {
        JTonnyLoading,
        TitleText,
        SmallBtn,
        HelperCard,
        LargeBtn,
        AlarmModal,
    },
    methods: {
        찾아짐() {
            this.isFind = true;
        },

        openNoticeModal() {
            this.isOpenNotice = true;
        },

        openCancelModal() {
            this.isOpenCancel = true;
        },

        closeModal() {
            this.isOpenNotice = false;
            this.isOpenCancel = false;
        },

        clickNoticeModal() {
            this.$router.push({ name: "LivePage" });
        },

        clickCancelModal() {
            this.$router.go(-1);
        },
    },
};
</script>

<style></style>
