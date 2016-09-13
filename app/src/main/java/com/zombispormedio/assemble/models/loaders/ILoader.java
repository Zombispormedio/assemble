package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public interface ILoader {
    void retrieve(final ISuccessHandler handler);
}
