package com.jeckonly.core_model.dto.pokemondetail

/**
 * api返回的宝可梦模型
 * 这个数据模型并不包含所有字段，即在映射到UI层之前就已做了删减，主要是很多和版本有关的无用详细信息
 */
data class PokemonDetailDto(

    /**
     * 特性（性格）列表
     */
    val abilities: List<Ability>,

    /**
     * 击败后得到的经验
     */
    val base_experience: Int,

    /**
     * 登场的游戏列表
     */
    val game_indices: List<GameIndice>,
    /**
     * 身高，单位分米
     */
    val height: Int,

    /**
     * 可能持有的物品
     */
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,

    /**
     * 技能列表
     */
    val moves: List<Move>,

    /**
     * 名字
     */
    val name: String,

    /**
     * 通过这个属性得到宝可梦的物种信息才可以得到进化链以及描述
     */
    val species: Species,

    /**
     * 各种图片
     */
    val sprites: Sprites,

    /**
     * 数值，hp,attack,,,,,,
     */
    val stats: List<Stat>,

    /**
     * 属性
     */
    val types: List<Type>,

    /**
     * 重量 百克
     */
    val weight: Int
)