package com.wangweijun.myapplication.myjava;

import android.util.Log;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/12 2:06 上午
 * version: 1.0
 * desc   :
 */
public class MainTimer {

    @Test
    public void testTimer() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("accept aLong="+aLong);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("exception ");
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tt() {
//        Observable<Integer>.create(new ObservableOnSubscribe<Integer>(){
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
//
//            }
//        });
    }
}
