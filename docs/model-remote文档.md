# Pokemon

*含有比较多的版本信息，这些信息在一期可以去掉。

```kotlin
data class PokemonDto(
    val abilities: List<Ability>,// 特性 ，一个宝可梦有多个特性可选，但一次只能有一个特性，类似于性格
    val base_experience: Int,// 击败得到的经验值
    val forms: List<Form>,// 可以变化的形态，一般都没有变化形态，这不是进化
    val game_indices: List<GameIndice>,// 登场的游戏列表
    val height: Int,// 宝可梦高度 分米
    val held_items: List<HeldItem>,// 宝可梦可能持有的物品（果实，精灵球...）
    val id: Int,// id
    val is_default: Boolean,// 物种默认宝可梦
    val location_area_encounters: String,// 指向位置区域列表的链接，以及与特定版本相关的详细信息。
    val moves: List<Move>,// 技能列表
    val name: String,// name
    val order: Int,// 可不要
    val past_types: List<Any>,// 和版本有关 可不要 
    val species: Species,// 物种，比如皮卡丘就属于皮卡丘这个物种
    val sprites: Sprites,// 图片集合，前面，侧面之类，可不要，自己封装url
    val stats: List<Stat>,// 攻击 防御等重要数据
    val types: List<Type>,// 草 电 毒等属性
    val weight: Int// 重量 百克
)
```

无用的属性：

```
forms,一般一个宝可梦都只有一个形态，该资源无用
location_area_encounters 包含各个版本的相遇地点，相遇细节，留着，就一个字符串
order
past_types
```

注意属性

```
species 
通过这个属性得到宝可梦的物种信息才可以得到进化链以及描述
```

