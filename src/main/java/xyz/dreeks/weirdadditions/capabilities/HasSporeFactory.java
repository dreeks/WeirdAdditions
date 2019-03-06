package xyz.dreeks.weirdadditions.capabilities;

import java.util.concurrent.Callable;

public class HasSporeFactory implements Callable<IHasSpore> {

    @Override
    public IHasSpore call() throws Exception {
        return new HasSpore();
    }

}
