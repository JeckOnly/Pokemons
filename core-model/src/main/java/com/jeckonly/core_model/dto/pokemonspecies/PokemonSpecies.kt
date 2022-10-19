package com.jeckonly.core_model.dto.pokemonspecies

data class PokemonSpecies(

    /**
     * 被Pokeball抓住时的开心值，最高255
     */
    val base_happiness: Int,

    /**
     * 基本捕获率；最多255。数字越高，越容易抓到。
     */
    val capture_rate: Int,

    /**
     * 颜色
     */
    val color: Color,

    /**
     * 蛋组
     */
    val egg_groups: List<EggGroup>,

    /**
     * 进化链url
     */
    val evolution_chain: EvolutionChain,

    /**
     * 太多了，怕耗内存
     */
//    val flavor_text_entries: List<FlavorTextEntry>,
//    val form_descriptions: List<Any>,
//    val forms_switchable: Boolean,
//    val gender_rate: Int,
//    val genera: List<Genera>,
//    val generation: Generation,

    /**
     * 成长速率等级
     */
    val growth_rate: GrowthRate,

    /**
     * 可能遇到的栖息地
     */
    val habitat: Habitat,
//    val has_gender_differences: Boolean,
//    val hatch_counter: Int,
    val id: Int,

    /**
     * 是否是宝宝宝可梦
     */
    val is_baby: Boolean,

    /**
     * 是否是传说宝可梦
     */
    val is_legendary: Boolean,

    /**
     * 是否是神话宝可梦
     */
    val is_mythical: Boolean,
    val name: String,
//    val names: List<Name>,
//    val order: Int,
//    val pal_park_encounters: List<PalParkEncounter>,
//    val pokedex_numbers: List<PokedexNumber>,

    /**
     * 外表形状，例如“四足动物”
     */
    val shape: Shape,
//    val varieties: List<Variety>
)