package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.SuccessHandler;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public interface ILoader {
    void retrieve(final SuccessHandler handler);
}
