package com.jeckonly.core_model.ui.home

sealed interface HomeScreenMode{

    /**
     * 浏览模式 展示当前所有小于page页的数据
     */
    object BrowseMode: HomeScreenMode

    /**
     * 搜索模式，展示当前搜索结果
     */
    object SearchMode: HomeScreenMode
}
