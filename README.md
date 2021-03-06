# MaterialDesignNote
> 对应笔记源码托管在[GIthub传送门](https://github.com/kscMaster/MaterialDesignNote) 
> 博客地址：[CSDN博客](https://blog.csdn.net/u012552275)
@[TOC]
# 材料设计核心UI组件

## 过渡布局
### CoordinatorLayout(ViewGroup)
1. fitsSystemWindows : 为系统的状态栏预留出空间
2. app:layout_scrollFlags // 子布局设置是否可滑动）
3. android:layout_gravity // 属性控制组件在布局中的位置
4. app:layout_behavior="@string/appbar_scrolling_view_behavior" 通知布局中包含滑动组件
### AppBarLayout(LinearLayout)
1. fitsSystemWindows : 为系统的状态栏预留出空间
### CollapsingToolbarLayout(FrameLayout)
1. app:collapsedTitleGravity // 指定折叠状态的标题如何放置，可选值:top、bottom等
2. app:collapsedTitleTextAppearance="@style/TextAppearance.CollapsedTitle" // 指定折叠状态标题文字的样貌
3. app:expandedTitleTextAppearance="@style/TextAppearance.ExpandedTitle" // 指定展开状态标题文字的样貌
4. app:contentScrim="?attr/colorPrimaryDark" // 指定CollapsingToolbarLayout完全被滚出到屏幕外时的ColorDrawable
5. app:expandedTitleGravity // 展开状态的标题如何放置
6. app:titleEnabled // 指定是否显示标题文本
7. app:toolbarId // 指定与之关联的ToolBar，如果未指定则默认使用第一个被发现的ToolBar子View
8. app:expandedTitleMarginStart="10dp"
9. app:expandedTitleMargin
10. app:expandedTitleMarginBottom
11. app:expandedTitleMarginEnd // 展开状态改变标题文字的位置，通过margin设置
12. app:layout_collapseParallaxMultiplier="0.7" // 设置视差的系数，介于0.0-1.0之间。
13. app:layout_collapseMode="pin"（子布局设置折叠模式） // 有两种“pin”：固定模式，在折叠的时候最后固定在顶端；“parallax”：视差模式，在折叠的时候会有个视差折叠的效果。
14. fitsSystemWindows : 为系统的状态栏预留出空间
15. app:layout_scrollFlags // 放到哪控件上，就代表哪个控件在操作的时候被折叠，其属性：
> scroll : 当behavior生效时，滑动滚动视图的时候，加上这个属性才可以控制toolbar内部的视图被折叠凳效果
> exitUntilCollapsed : 向上滚动时收缩View，但固定Toolbar一直保持不动
> enterAlways : 无论何种场景，只要下滑动，顶部被折叠的Toolbar都将立即显示
> enterAlwaysCollapsed : View一直处于折叠状态，直至滚动视图滚到到顶部时View才会展开
> snap : 设置吸附效果

#### 一般搭配效果：
1. app:layout_scrollFlags=”noScroll” // 当滚动视图滑动时，头部的TabLayout是不会跟随滑动的
2. app:layout_scrollFlags=”scroll” // 当向上滑动时，Toolbar跟随滑动，下拉时拉到滚动视图的顶部头部Toolbar才会显示出来
3. app:layout_scrollFlags=”scroll|enterAlways” // 当向上滑动时，Toolbar跟随滑动，只要向下拉，Toolbar就立即显示出来
4. app:layout_scrollFlags=”scroll|enterAlways|enterAlwaysCollapsed” // 向上滑动时，Toolbar立即跟随隐藏；向下滑动时，首先显示Toolbar内容，当滑到顶部时，才显示Toolbar内部包含的内容
5. app:layout_scrollFlags=”scroll|exitUntilCollapsed” // 当向上滑动时，Toolbar内部包含的内容会隐藏，但Toolbar固定；当向下滑动到顶部时，Toolbar内部隐藏内容才会显示出来
6. app:layout_scrollFlags=”scroll|snap” // 设置吸附效果，当滚动举例不足高度一半回弹

### Toolbar(ViewGroup)
1. app:layout_scrollFlags的属性:同上
> 当Toolbar设置此元素时，不可以使用*exitUntilCollapsed*属性值，因为设置此元素是希望Toolbar被隐藏，但其属性值却表示Toolbar固定，相互冲突，故不可同时使用
app:layout_collapseMode:
> pin 
> parallax
> none
### NestedScrollView(FrameLayout)
1. tools:showIn="@layout/activity_scrolling"
2. layout_behavior
## 菜单控件
### TabLayout(HorizontalScrollView)
1. app:tabIndicatorColor // tab的指示符颜色
2. app:tabSelectedTextColor  //　选择tab的文本颜色
3. app:tabTextColor // 普通tab字体颜色
4. app:tabMode // 模式，可选fixed和scrollable fixed是指固定个数，scrollable是可以横行滚动app:tabGravity // 对齐方式，可选fill和center
### NavigationView(FrameLayout)

## 常规控件
### RecyclerView

### CardView

### FloatingActionButton
1. layout_anchor // 依附到某控件上(@id/xxx)
2. layout_anchorGravity // 依附的控件的位置
3. srcCompat // 背景资源图
4. app:fabSize="normal|mini" // 是用来定义大小
5. app:elevation // 为空闲状态下的阴影深度
6. app:pressedTranslationZ // 为按下状态
7. app:backgroundTint // 是指定默认的背景颜色
8. app:rippleColor // 是指定点击时的背景颜色
9. app:borderWidth //　border的宽度
### TextInputLayout

### Snackbar

### SwipeRefreshLayout

## 动画
### 圆心扩散动画
系统提供了一个圆心扩散的API
```java
public static Animator createCircularReveal(View view, int centerX, int centerY, float startRadius, float endRadius)
```
// 一般用法
```java
Animator an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, view.getWidth());
        an.setInterpolator(new AccelerateDecelerateInterpolator());
        an.setDuration(4000);
        an.start();
        an.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(ScrollingActivity.this, NavigationDrawerActivity.class));
            }
        });
```
// 经过抽象可以这样做：
```java
    public Animator initAnimator(View view, int style, int duration, Interpolator interpolator) {
        Animator an = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (style) {
                case 1: // 从控件的圆心扩散到控件的宽度
                    an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, view.getWidth());
                    break;
                case 2: // 从控件的宽度的一半为圆心扩散到控件的高度
                    an = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, 0, 0, view.getHeight());
                    break;

            }
            an.setInterpolator(interpolator);
            an.setDuration(duration);
            return an;
        } else return null;
    }

    public void startAnimation(View view, int style, int duration, Interpolator interpolator) {
        Animator animator = initAnimator(view, style, duration, interpolator);
        if (animator != null) animator.start();
    }
```
#### 插值器总结
| 作用                                 | 资源ID                                           | 对应的Java类                     |
|--------------------------------------|--------------------------------------------------|----------------------------------|--|
| 动画加速进行                         | @android:anim/accelerate_interpolator            | AccelerateInterpolator           |
| 快速完成动画，超出再回到结束样式     | @android:anim/overshoot_interpolator             | OvershootInterpolator            |
| 先加速再减速                         | @android:anim/accelerate_decelerate_interpolator | AccelerateDecelerateInterpolator |
| 先退后再加速前进                     | @android:anim/anticipate_interpolator            | AnticipateInterpolator           |
| 先退后再加速前进，超出终点后再回终点 | @android:anim/anticipate_overshoot_interpolator  | AnticipateOvershootInterpolator  |
| 最后阶段弹球效果                     | @android:anim/bounce_interpolator                | BounceInterpolator               |
| 周期运动                             | @android:anim/cycle_interpolator                 | CycleInterpolator                |
| 减速                                 | @android:anim/decelerate_interpolator            | DecelerateInterpolator           |
| 匀速                                 | @android:anim/linear_interpolator                | LinearInterpolator               |
### 元素共享转场动画
```java
    /**
     * 实现共享元素场景切换动画(转场动画)
     */
    public void transferActivity(Class cls, Pair<View, String>... sharedElements) {
        // 启动页面跳转
        Intent intent = new Intent();
        intent.setClass(ScrollingActivity.this, cls);
        Transition ts = new ChangeImageTransform();
        ts.setDuration(3000);
        getWindow().setExitTransition(ts);

        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, sharedElements);
        Bundle b = op.toBundle();
        startActivity(intent, b);

    }
    // 调用：
    // transferActivity(NavigationDrawerActivity.class, Pair.create((View) iv_image, "banner"), Pair.create((View) iv_image, "banner2"));
    // iv_image是本Activity内的控件ID，banner为目标跳转的Activity中布局中的transitionName,也就是说，目标cls中布局的共享元素需要加一条属性*android:transitionName="banner"*

```
