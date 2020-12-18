package top.iqqcode.hitoday.bean;

public class LunarBean {

    /**
     * reason : successed
     * result : {"id":"3883","yangli":"2020-12-16","yinli":"庚子(鼠)年十一月初二","wuxing":"长流水 执执位","chongsha":"冲猪(丁亥)煞东","baiji":"癸不词讼理弱敌强 已不远行财物伏藏","jishen":"五富 益後","yi":"祭祀 塑绘 开光 裁衣 冠笄 嫁娶 纳采 拆卸 修造 动土 竖柱 上梁 安床 移徙 入宅 安香 结网 捕捉 畋猎 伐木 进人口 放水","xiongshen":"劫煞 小耗 复日 重日 元武","ji":"出行 安葬 修坟 开市"}
     * error_code : 0
     */

    private String reason;
    /**
     * id : 3883
     * yangli : 2020-12-16
     * yinli : 庚子(鼠)年十一月初二
     * wuxing : 长流水 执执位
     * chongsha : 冲猪(丁亥)煞东
     * baiji : 癸不词讼理弱敌强 已不远行财物伏藏
     * jishen : 五富 益後
     * yi : 祭祀 塑绘 开光 裁衣 冠笄 嫁娶 纳采 拆卸 修造 动土 竖柱 上梁 安床 移徙 入宅 安香 结网 捕捉 畋猎 伐木 进人口 放水
     * xiongshen : 劫煞 小耗 复日 重日 元武
     * ji : 出行 安葬 修坟 开市
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }
    }
}
