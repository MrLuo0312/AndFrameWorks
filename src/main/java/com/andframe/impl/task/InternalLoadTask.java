package com.andframe.impl.task;

/**
 * 内部加载任务实现
 * Created by SCWANG on 2017/4/28.
 */
@SuppressWarnings("WeakerAccess")
public class InternalLoadTask<T> extends InternalTask {

    protected T data;
    protected LoadTaskBuilder<T> builder;

    public InternalLoadTask(LoadTaskBuilder<T> builder) {
        super(builder);
        this.builder = builder;
    }

    @Override
    protected void onWorking() throws Exception {
        super.onWorking();
        if (builder.loadingHandler != null) {
            data = builder.loadingHandler.onLoading();
        }
    }

    @Override
    protected void onHandle() {
        super.onHandle();
        if (isFinish()) {
            if (isEmpty(data)) {
                if (builder.emptyRunnable != null) {
                    builder.emptyRunnable.run();
                }
            } else {
                if (builder.loadSuccessHandler != null) {
                    builder.loadSuccessHandler.onSuccess(data);
                }
            }
        }
    }

    private boolean isEmpty(T data) {
        if (builder.isEmptyHandler != null) {
            return builder.isEmptyHandler.isEmpty(data);
        }
//        if (data instanceof Collection) {
//            return ((Collection) data).isEmpty();
//        }
        return data == null;
    }
}
