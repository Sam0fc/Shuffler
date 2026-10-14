# Block Shuffle item ranking (final settings, includes stand-on blocks)

Pool = crafting-propagated drops (max-entropy model: obtainability priors, which count recipe yields for ingredients but need only one of the item itself, and make bulk gathering cheaper (each further unit of a raw material, counted through every craft the recipe needs, costs 0.85 of the one before), with exact per-primitive budgets; recipes that undo or sidestep another are ignored) PLUS the literal blocks a player can stand on, as in ConstantItemGenerator.getBagForPlayers. Occlusion cost scale 1, mob drop weight 140. The propagated bag and the stand-on bag each make up half of the final weight, game level 1, AIR drops filtered.
Source: "drop" = obtainable by breaking a block / killing a mob, "block" = can be stood on, "crafted" = only reachable through recipes.

## A. Pooled over 7 biome start points

Average of per-biome shares: taiga, forest, plains, dark_forest, birch_forest, savanna, desert.

| # | Item | Chance % | Times rarer than #1 | Source |
|---|---|---|---|---|
| 1 | DIRT | 34.97 | 1 | drop block |
| 2 | STONE | 10.29 | 3.4 | block |
| 3 | GRASS_BLOCK | 7.027 | 4.98 | block |
| 4 | WATER | 5.635 | 6.21 | block |
| 5 | OAK_LEAVES | 3.287 | 10.6 | block |
| 6 | LEAF_LITTER | 3.138 | 11.1 | drop block |
| 7 | BIRCH_LEAVES | 2.467 | 14.2 | block |
| 8 | SAND | 2.243 | 15.6 | drop block |
| 9 | SPRUCE_LEAVES | 1.923 | 18.2 | block |
| 10 | SANDSTONE | 1.483 | 23.6 | drop block |
| 11 | COBBLESTONE | 1.217 | 28.7 | drop block |
| 12 | SMOOTH_STONE_SLAB | 1.112 | 31.5 | drop block |
| 13 | STONE_BUTTON | 1.109 | 31.5 | crafted |
| 14 | SMOOTH_STONE | 1.109 | 31.5 | crafted |
| 15 | STONE_BRICK_SLAB | 0.9769 | 35.8 | drop block |
| 16 | CHISELED_STONE_BRICKS | 0.9753 | 35.9 | crafted |
| 17 | STONE_BRICK_STAIRS | 0.9546 | 36.6 | drop block |
| 18 | STONE_BRICK_WALL | 0.9539 | 36.7 | crafted |
| 19 | DARK_OAK_LEAVES | 0.8853 | 39.5 | block |
| 20 | GLASS | 0.7079 | 49.4 | crafted |
| 21 | SHORT_GRASS | 0.6382 | 54.8 | block |
| 22 | SMOOTH_SANDSTONE | 0.6382 | 54.8 | crafted |
| 23 | STONE_PRESSURE_PLATE | 0.5997 | 58.3 | crafted |
| 24 | SNOW | 0.4947 | 70.7 | block |
| 25 | COBBLESTONE_SLAB | 0.4321 | 80.9 | drop block |
| 26 | STONE_SLAB | 0.4313 | 81.1 | crafted |
| 27 | GRAVEL | 0.4109 | 85.1 | drop block |
| 28 | ANDESITE | 0.4059 | 86.2 | drop block |
| 29 | PALE_MOSS_BLOCK | 0.3534 | 98.9 | drop block |
| 30 | STONE_BRICKS | 0.3499 | 99.9 | drop block |
| 31 | CRACKED_STONE_BRICKS | 0.3494 | 100 | drop block |
| 32 | GRANITE | 0.313 | 112 | drop block |
| 33 | DARK_OAK_LOG | 0.3012 | 116 | drop block |
| 34 | DIORITE | 0.2937 | 119 | drop block |
| 35 | CHISELED_SANDSTONE | 0.2855 | 122 | crafted |
| 36 | SANDSTONE_SLAB | 0.2855 | 122 | crafted |
| 37 | SMOOTH_SANDSTONE_SLAB | 0.2855 | 122 | crafted |
| 38 | COBBLESTONE_STAIRS | 0.282 | 124 | drop block |
| 39 | COBBLESTONE_WALL | 0.2803 | 125 | drop block |
| 40 | OAK_LOG | 0.2776 | 126 | drop block |
| 41 | GLASS_BOTTLE | 0.2752 | 127 | crafted |
| 42 | STONE_STAIRS | 0.2672 | 131 | crafted |
| 43 | FEATHER | 0.2632 | 133 | drop |
| 44 | BIRCH_LOG | 0.2629 | 133 | drop block |
| 45 | SPRUCE_LOG | 0.2623 | 133 | drop block |
| 46 | CUT_SANDSTONE_SLAB | 0.2453 | 143 | crafted |
| 47 | CUT_SANDSTONE | 0.2453 | 143 | crafted |
| 48 | FURNACE | 0.2287 | 153 | crafted |
| 49 | BIRCH_SAPLING | 0.2111 | 166 | drop |
| 50 | SANDSTONE_STAIRS | 0.2086 | 168 | crafted |
| 51 | SANDSTONE_WALL | 0.2086 | 168 | crafted |
| 52 | SMOOTH_SANDSTONE_STAIRS | 0.2086 | 168 | crafted |
| 53 | STICK | 0.1964 | 178 | drop |
| 54 | COARSE_DIRT | 0.1928 | 181 | drop block |
| 55 | ACACIA_LEAVES | 0.1909 | 183 | block |
| 56 | GLASS_PANE | 0.1774 | 197 | block |
| 57 | WILDFLOWERS | 0.1731 | 202 | drop block |
| 58 | CHICKEN | 0.1729 | 202 | drop |
| 59 | COOKED_CHICKEN | 0.1729 | 202 | crafted |
| 60 | PALE_OAK_LEAVES | 0.1628 | 215 | block |
| 61 | ICE | 0.1614 | 217 | block |
| 62 | DARK_OAK_PLANKS | 0.1583 | 221 | drop block |
| 63 | COAL_ORE | 0.1284 | 272 | block |
| 64 | BIRCH_PLANKS | 0.1237 | 283 | drop block |
| 65 | PALE_MOSS_CARPET | 0.123 | 284 | drop block |
| 66 | COAL | 0.1185 | 295 | drop |
| 67 | PODZOL | 0.108 | 324 | block |
| 68 | BEEF | 0.1008 | 347 | drop |
| 69 | COOKED_BEEF | 0.1008 | 347 | crafted |
| 70 | PORKCHOP | 0.09968 | 351 | drop |
| 71 | COOKED_PORKCHOP | 0.09968 | 351 | crafted |
| 72 | PALE_OAK_LOG | 0.0901 | 388 | drop block |
| 73 | LEVER | 0.08457 | 413 | drop block |
| 74 | STONE_SWORD | 0.07615 | 459 | crafted |
| 75 | BROWN_WOOL | 0.07225 | 484 | drop block |
| 76 | YELLOW_DYE | 0.071 | 493 | crafted |
| 77 | ANDESITE_SLAB | 0.07085 | 494 | crafted |
| 78 | STONE_SHOVEL | 0.0692 | 505 | crafted |
| 79 | STONE_SPEAR | 0.0692 | 505 | crafted |
| 80 | STONE_HOE | 0.06425 | 544 | crafted |
| 81 | POLISHED_ANDESITE | 0.06138 | 570 | drop block |
| 82 | LEATHER | 0.06138 | 570 | drop |
| 83 | POLISHED_ANDESITE_SLAB | 0.06135 | 570 | crafted |
| 84 | STONE_AXE | 0.06049 | 578 | crafted |
| 85 | STONE_PICKAXE | 0.06049 | 578 | crafted |
| 86 | TALL_GRASS | 0.05414 | 646 | block |
| 87 | BOWL | 0.05377 | 650 | crafted |
| 88 | CHARCOAL | 0.05252 | 666 | crafted |
| 89 | CRAFTING_TABLE | 0.05171 | 676 | crafted |
| 90 | TORCH | 0.04978 | 702 | drop block |
| 91 | WHITE_WOOL | 0.0492 | 711 | drop block |
| 92 | WHITE_CARPET | 0.04784 | 731 | drop block |
| 93 | WOODEN_SHOVEL | 0.04743 | 737 | crafted |
| 94 | WOODEN_SPEAR | 0.04743 | 737 | crafted |
| 95 | ANDESITE_STAIRS | 0.04601 | 760 | crafted |
| 96 | ANDESITE_WALL | 0.04601 | 760 | crafted |
| 97 | GRANITE_SLAB | 0.04445 | 787 | crafted |
| 98 | WOODEN_SWORD | 0.04182 | 836 | crafted |
| 99 | FERN | 0.04092 | 854 | block |
| 100 | WOODEN_HOE | 0.04013 | 871 | crafted |
| 101 | PALE_HANGING_MOSS | 0.03974 | 880 | block |
| 102 | LADDER | 0.03973 | 880 | drop block |
| 103 | COPPER_ORE | 0.03916 | 893 | block |
| 104 | BROWN_CARPET | 0.03914 | 893 | drop block |
| 105 | POLISHED_ANDESITE_STAIRS | 0.03864 | 905 | crafted |
| 106 | WHEAT_SEEDS | 0.03843 | 910 | drop |
| 107 | WOODEN_AXE | 0.03772 | 927 | crafted |
| 108 | WOODEN_PICKAXE | 0.03772 | 927 | crafted |
| 109 | ARMOR_STAND | 0.03742 | 934 | crafted |
| 110 | RABBIT | 0.03723 | 939 | drop |
| 111 | COOKED_RABBIT | 0.03723 | 939 | crafted |
| 112 | GRINDSTONE | 0.03617 | 967 | crafted |
| 113 | POLISHED_GRANITE | 0.03588 | 974 | crafted |
| 114 | POLISHED_GRANITE_SLAB | 0.03588 | 974 | crafted |
| 115 | NETHERRACK | 0.0351 | 996 | drop block |
| 116 | LARGE_FERN | 0.03318 | 1.05e+03 | block |
| 117 | RED_CARPET | 0.033 | 1.06e+03 | drop block |
| 118 | CHEST | 0.03038 | 1.15e+03 | drop block |
| 119 | DARK_OAK_BUTTON | 0.03012 | 1.16e+03 | crafted |
| 120 | BROWN_WOOL_SLAB | 0.02802 | 1.25e+03 | crafted |
| 121 | BROWN_CUSHION | 0.02802 | 1.25e+03 | crafted |
| 122 | GRANITE_STAIRS | 0.02754 | 1.27e+03 | crafted |
| 123 | GRANITE_WALL | 0.02754 | 1.27e+03 | crafted |
| 124 | PAINTING | 0.02741 | 1.28e+03 | crafted |
| 125 | BROWN_MUSHROOM_BLOCK | 0.02621 | 1.33e+03 | block |
| 126 | RABBIT_HIDE | 0.02456 | 1.42e+03 | drop |
| 127 | OAK_PLANKS | 0.02454 | 1.42e+03 | drop block |
| 128 | MOSSY_COBBLESTONE | 0.02438 | 1.43e+03 | drop block |
| 129 | POLISHED_GRANITE_STAIRS | 0.02358 | 1.48e+03 | crafted |
| 130 | COAL_BLOCK | 0.02313 | 1.51e+03 | crafted |
| 131 | BIRCH_BUTTON | 0.02221 | 1.57e+03 | crafted |
| 132 | DIORITE_SLAB | 0.02205 | 1.59e+03 | crafted |
| 133 | ACACIA_LOG | 0.02196 | 1.59e+03 | drop block |
| 134 | DARK_OAK_FENCE | 0.02135 | 1.64e+03 | drop block |
| 135 | DARK_OAK_PRESSURE_PLATE | 0.02086 | 1.68e+03 | crafted |
| 136 | LEATHER_BOOTS | 0.0208 | 1.68e+03 | crafted |
| 137 | COMPOSTER | 0.02001 | 1.75e+03 | crafted |
| 138 | OAK_SLAB | 0.01981 | 1.76e+03 | drop block |
| 139 | OAK_STAIRS | 0.01961 | 1.78e+03 | drop block |
| 140 | WHITE_WOOL_SLAB | 0.01893 | 1.85e+03 | crafted |
| 141 | WHITE_CUSHION | 0.01893 | 1.85e+03 | crafted |
| 142 | LEATHER_HELMET | 0.01828 | 1.91e+03 | crafted |
| 143 | DARK_OAK_SLAB | 0.01817 | 1.92e+03 | crafted |
| 144 | BROWN_MUSHROOM | 0.01787 | 1.96e+03 | drop block |
| 145 | POLISHED_DIORITE | 0.0178 | 1.96e+03 | crafted |
| 146 | POLISHED_DIORITE_SLAB | 0.0178 | 1.96e+03 | crafted |
| 147 | CAMPFIRE | 0.01749 | 2e+03 | crafted |
| 148 | BROWN_WOOL_STAIRS | 0.01736 | 2.01e+03 | crafted |
| 149 | BARREL | 0.01617 | 2.16e+03 | crafted |
| 150 | CHISELED_BOOKSHELF | 0.01612 | 2.17e+03 | crafted |
| 151 | LEATHER_HORSE_ARMOR | 0.01555 | 2.25e+03 | crafted |
| 152 | LEATHER_LEGGINGS | 0.01555 | 2.25e+03 | crafted |
| 153 | SMOKER | 0.01538 | 2.27e+03 | crafted |
| 154 | BIRCH_PRESSURE_PLATE | 0.01537 | 2.27e+03 | crafted |
| 155 | OAK_BUTTON | 0.01521 | 2.3e+03 | crafted |
| 156 | OAK_PRESSURE_PLATE | 0.01494 | 2.34e+03 | crafted |
| 157 | LEATHER_CHESTPLATE | 0.01475 | 2.37e+03 | crafted |
| 158 | RED_MUSHROOM_BLOCK | 0.01452 | 2.41e+03 | block |
| 159 | OAK_FENCE | 0.01435 | 2.44e+03 | drop block |
| 160 | BIRCH_SLAB | 0.01419 | 2.46e+03 | drop block |
| 161 | IRON_ORE | 0.0141 | 2.48e+03 | block |
| 162 | TALL_SEAGRASS | 0.01379 | 2.54e+03 | block |
| 163 | DIORITE_STAIRS | 0.01366 | 2.56e+03 | crafted |
| 164 | DIORITE_WALL | 0.01366 | 2.56e+03 | crafted |
| 165 | BLACK_WOOL | 0.01306 | 2.68e+03 | drop block |
| 166 | DARK_OAK_STAIRS | 0.01253 | 2.79e+03 | drop block |
| 167 | KELP | 0.0125 | 2.8e+03 | drop block |
| 168 | SEAGRASS | 0.01216 | 2.88e+03 | block |
| 169 | SPRUCE_PLANKS | 0.01186 | 2.95e+03 | crafted |
| 170 | SPRUCE_PRESSURE_PLATE | 0.01186 | 2.95e+03 | crafted |
| 171 | SPRUCE_SLAB | 0.01186 | 2.95e+03 | crafted |
| 172 | SPRUCE_BUTTON | 0.01186 | 2.95e+03 | crafted |
| 173 | WHITE_WOOL_STAIRS | 0.01174 | 2.98e+03 | crafted |
| 174 | POLISHED_DIORITE_STAIRS | 0.0117 | 2.99e+03 | crafted |
| 175 | CLAY | 0.01166 | 3e+03 | block |
| 176 | KELP_PLANT | 0.01158 | 3.02e+03 | block |
| 177 | DARK_OAK_FENCE_GATE | 0.01146 | 3.05e+03 | drop block |
| 178 | DARK_OAK_BOAT | 0.01087 | 3.22e+03 | crafted |
| 179 | BIRCH_FENCE_GATE | 0.01061 | 3.3e+03 | crafted |
| 180 | DARK_OAK_DOOR | 0.01035 | 3.38e+03 | crafted |
| 181 | DARK_OAK_TRAPDOOR | 0.01035 | 3.38e+03 | crafted |
| 182 | YELLOW_WOOL | 0.01024 | 3.42e+03 | drop block |
| 183 | VINE | 0.009858 | 3.55e+03 | block |
| 184 | BIRCH_FENCE | 0.009766 | 3.58e+03 | drop block |
| 185 | SPRUCE_FENCE | 0.009102 | 3.84e+03 | crafted |
| 186 | DRIED_KELP | 0.009059 | 3.86e+03 | crafted |
| 187 | NETHER_BRICK | 0.009001 | 3.88e+03 | crafted |
| 188 | GRAY_WOOL | 0.008972 | 3.9e+03 | drop block |
| 189 | BIRCH_STAIRS | 0.008694 | 4.02e+03 | drop block |
| 190 | BUSH | 0.008179 | 4.28e+03 | block |
| 191 | BIRCH_BOAT | 0.008067 | 4.33e+03 | crafted |
| 192 | OAK_BOAT | 0.008064 | 4.34e+03 | crafted |
| 193 | OAK_TRAPDOOR | 0.008051 | 4.34e+03 | crafted |
| 194 | OAK_DOOR | 0.008051 | 4.34e+03 | crafted |
| 195 | OAK_FENCE_GATE | 0.008035 | 4.35e+03 | crafted |
| 196 | SPRUCE_FENCE_GATE | 0.007953 | 4.4e+03 | crafted |
| 197 | BIRCH_TRAPDOOR | 0.00778 | 4.49e+03 | crafted |
| 198 | BIRCH_DOOR | 0.00778 | 4.49e+03 | crafted |
| 199 | DANDELION | 0.007382 | 4.74e+03 | drop block |
| 200 | DARK_OAK_SIGN | 0.007292 | 4.8e+03 | crafted |
| 201 | LIGHT_GRAY_WOOL | 0.007086 | 4.93e+03 | drop block |
| 202 | BLACK_CARPET | 0.007008 | 4.99e+03 | crafted |
| 203 | ITEM_FRAME | 0.006952 | 5.03e+03 | crafted |
| 204 | PALE_OAK_PLANKS | 0.006911 | 5.06e+03 | crafted |
| 205 | PALE_OAK_PRESSURE_PLATE | 0.006911 | 5.06e+03 | crafted |
| 206 | PALE_OAK_SLAB | 0.006911 | 5.06e+03 | crafted |
| 207 | PALE_OAK_BUTTON | 0.006911 | 5.06e+03 | crafted |
| 208 | PINK_TULIP | 0.006794 | 5.15e+03 | drop block |
| 209 | WHITE_BANNER | 0.006761 | 5.17e+03 | crafted |
| 210 | LILAC | 0.006522 | 5.36e+03 | block |
| 211 | SPRUCE_STAIRS | 0.006413 | 5.45e+03 | crafted |
| 212 | SPRUCE_TRAPDOOR | 0.006413 | 5.45e+03 | crafted |
| 213 | SPRUCE_BOAT | 0.006413 | 5.45e+03 | crafted |
| 214 | SPRUCE_DOOR | 0.006413 | 5.45e+03 | crafted |
| 215 | SPRUCE_SIGN | 0.006388 | 5.47e+03 | crafted |
| 216 | BIRCH_SIGN | 0.006266 | 5.58e+03 | crafted |
| 217 | DRIPSTONE_BLOCK | 0.006159 | 5.68e+03 | drop block |
| 218 | YELLOW_CARPET | 0.006097 | 5.74e+03 | crafted |
| 219 | DARK_OAK_CHEST_BOAT | 0.005822 | 6.01e+03 | crafted |
| 220 | GRAY_CARPET | 0.005817 | 6.01e+03 | drop block |
| 221 | BIRCH_CHEST_BOAT | 0.005771 | 6.06e+03 | crafted |
| 222 | POPPY | 0.005727 | 6.11e+03 | drop block |
| 223 | JUNGLE_LEAVES | 0.005677 | 6.16e+03 | block |
| 224 | WHITE_BED | 0.005601 | 6.24e+03 | crafted |
| 225 | PALE_OAK_FENCE | 0.00559 | 6.26e+03 | crafted |
| 226 | PALE_OAK_FENCE_GATE | 0.00559 | 6.26e+03 | crafted |
| 227 | OAK_SIGN | 0.005367 | 6.52e+03 | crafted |
| 228 | BLACK_WOOL_SLAB | 0.00504 | 6.94e+03 | crafted |
| 229 | BLACK_CUSHION | 0.00504 | 6.94e+03 | crafted |
| 230 | COPPER_TORCH | 0.005022 | 6.96e+03 | crafted |
| 231 | RAW_COPPER | 0.004888 | 7.15e+03 | drop |
| 232 | COPPER_INGOT | 0.004888 | 7.15e+03 | crafted |
| 233 | COPPER_NUGGET | 0.004888 | 7.15e+03 | crafted |
| 234 | BROWN_HARNESS | 0.004807 | 7.27e+03 | crafted |
| 235 | YELLOW_CUSHION | 0.004766 | 7.34e+03 | crafted |
| 236 | YELLOW_WOOL_SLAB | 0.004766 | 7.34e+03 | crafted |
| 237 | OAK_WOOD | 0.004632 | 7.55e+03 | crafted |
| 238 | WHITE_HARNESS | 0.004629 | 7.55e+03 | crafted |
| 239 | OAK_CHEST_BOAT | 0.004484 | 7.8e+03 | crafted |
| 240 | MUSHROOM_STEW | 0.004477 | 7.81e+03 | crafted |
| 241 | MUSHROOM_STEM | 0.004467 | 7.83e+03 | block |
| 242 | SUGAR_CANE | 0.004358 | 8.02e+03 | drop block |
| 243 | MOSSY_COBBLESTONE_SLAB | 0.004139 | 8.45e+03 | crafted |
| 244 | LIGHT_GRAY_CARPET | 0.004029 | 8.68e+03 | drop block |
| 245 | DARK_OAK_WOOD | 0.003811 | 9.17e+03 | crafted |
| 246 | SPRUCE_CHEST_BOAT | 0.003805 | 9.19e+03 | crafted |
| 247 | PALE_OAK_STAIRS | 0.003736 | 9.36e+03 | crafted |
| 248 | PALE_OAK_TRAPDOOR | 0.003736 | 9.36e+03 | crafted |
| 249 | PALE_OAK_BOAT | 0.003736 | 9.36e+03 | crafted |
| 250 | PALE_OAK_DOOR | 0.003736 | 9.36e+03 | crafted |
| 251 | BIRCH_WOOD | 0.003726 | 9.38e+03 | crafted |
| 252 | SPRUCE_WOOD | 0.003723 | 9.39e+03 | crafted |
| 253 | MAGMA_BLOCK | 0.003659 | 9.56e+03 | drop block |
| 254 | BRUSH | 0.003538 | 9.88e+03 | crafted |
| 255 | YELLOW_WOOL_STAIRS | 0.00347 | 1.01e+04 | crafted |
| 256 | GRAY_WOOL_SLAB | 0.003453 | 1.01e+04 | crafted |
| 257 | GRAY_CUSHION | 0.003453 | 1.01e+04 | crafted |
| 258 | COPPER_SHOVEL | 0.003359 | 1.04e+04 | crafted |
| 259 | COPPER_SPEAR | 0.003359 | 1.04e+04 | crafted |
| 260 | TOTEM_OF_UNDYING | 0.003351 | 1.04e+04 | drop |
| 261 | PALE_OAK_SIGN | 0.003304 | 1.06e+04 | crafted |
| 262 | CLAY_BALL | 0.003193 | 1.09e+04 | drop |
| 263 | BRICK | 0.003193 | 1.09e+04 | crafted |
| 264 | BLACK_WOOL_STAIRS | 0.003122 | 1.12e+04 | crafted |
| 265 | OXEYE_DAISY | 0.003106 | 1.13e+04 | drop block |
| 266 | ROSE_BUSH | 0.003076 | 1.14e+04 | block |
| 267 | TALL_DRY_GRASS | 0.002963 | 1.18e+04 | block |
| 268 | YELLOW_BED | 0.002949 | 1.19e+04 | crafted |
| 269 | JUNGLE_LOG | 0.002942 | 1.19e+04 | drop block |
| 270 | PALE_OAK_CHEST_BOAT | 0.002928 | 1.19e+04 | crafted |
| 271 | LIGHT_GRAY_WOOL_SLAB | 0.002858 | 1.22e+04 | crafted |
| 272 | LIGHT_GRAY_CUSHION | 0.002856 | 1.22e+04 | crafted |
| 273 | NETHER_BRICKS | 0.002825 | 1.24e+04 | crafted |
| 274 | CHISELED_NETHER_BRICKS | 0.002825 | 1.24e+04 | crafted |
| 275 | NETHER_BRICK_SLAB | 0.002825 | 1.24e+04 | crafted |
| 276 | NETHER_BRICK_STAIRS | 0.002825 | 1.24e+04 | crafted |
| 277 | NETHER_BRICK_WALL | 0.002825 | 1.24e+04 | crafted |
| 278 | CRACKED_NETHER_BRICKS | 0.002825 | 1.24e+04 | crafted |
| 279 | DEAD_BUSH | 0.002674 | 1.31e+04 | block |
| 280 | POINTED_DRIPSTONE | 0.002656 | 1.32e+04 | drop block |
| 281 | COPPER_CHAIN | 0.002642 | 1.32e+04 | crafted |
| 282 | GRAY_HARNESS | 0.002613 | 1.34e+04 | crafted |
| 283 | MOSSY_COBBLESTONE_STAIRS | 0.002565 | 1.36e+04 | crafted |
| 284 | MOSSY_COBBLESTONE_WALL | 0.002565 | 1.36e+04 | crafted |
| 285 | CACTUS | 0.002549 | 1.37e+04 | drop block |
| 286 | COPPER_LANTERN | 0.002538 | 1.38e+04 | crafted |
| 287 | BLACK_BANNER | 0.002497 | 1.4e+04 | crafted |
| 288 | SHORT_DRY_GRASS | 0.002454 | 1.42e+04 | block |
| 289 | RED_TULIP | 0.002347 | 1.49e+04 | drop block |
| 290 | YELLOW_BANNER | 0.002342 | 1.49e+04 | crafted |
| 291 | LIGHT_GRAY_WOOL_STAIRS | 0.002309 | 1.51e+04 | crafted |
| 292 | PALE_OAK_WOOD | 0.002169 | 1.61e+04 | crafted |
| 293 | COPPER_SWORD | 0.00216 | 1.62e+04 | crafted |
| 294 | GRAY_WOOL_STAIRS | 0.002158 | 1.62e+04 | crafted |
| 295 | COPPER_HOE | 0.002084 | 1.68e+04 | crafted |
| 296 | BLACK_BED | 0.002002 | 1.75e+04 | crafted |
| 297 | DEAD_BRAIN_CORAL_BLOCK | 0.001973 | 1.77e+04 | drop |
| 298 | PEONY | 0.001963 | 1.78e+04 | drop block |
| 299 | LIGHT_GRAY_BED | 0.001955 | 1.79e+04 | crafted |
| 300 | WHITE_TULIP | 0.001952 | 1.79e+04 | drop block |
| 301 | AZURE_BLUET | 0.001911 | 1.83e+04 | drop block |
| 302 | LIGHTNING_ROD | 0.0019 | 1.84e+04 | crafted |
| 303 | GRAY_BED | 0.00188 | 1.86e+04 | crafted |
| 304 | FLOWER_POT | 0.00187 | 1.87e+04 | drop |
| 305 | GRAY_BANNER | 0.001854 | 1.89e+04 | drop |
| 306 | MOSS_BLOCK | 0.001833 | 1.91e+04 | drop block |
| 307 | DRIED_KELP_BLOCK | 0.001768 | 1.98e+04 | crafted |
| 308 | BOOKSHELF | 0.001747 | 2e+04 | block |
| 309 | DEAD_TUBE_CORAL_BLOCK | 0.001715 | 2.04e+04 | drop |
| 310 | SUGAR | 0.001705 | 2.05e+04 | crafted |
| 311 | CLOSED_EYEBLOSSOM | 0.001688 | 2.07e+04 | drop block |
| 312 | ACACIA_STAIRS | 0.001602 | 2.18e+04 | drop block |
| 313 | COPPER_AXE | 0.001585 | 2.21e+04 | crafted |
| 314 | COPPER_PICKAXE | 0.001585 | 2.21e+04 | crafted |
| 315 | PINK_CARPET | 0.001572 | 2.22e+04 | drop block |
| 316 | LAPIS_ORE | 0.001535 | 2.28e+04 | block |
| 317 | COPPER_BOOTS | 0.001534 | 2.28e+04 | crafted |
| 318 | COPPER_TRAPDOOR | 0.001534 | 2.28e+04 | crafted |
| 319 | DEAD_BUBBLE_CORAL_BLOCK | 0.001518 | 2.3e+04 | drop |
| 320 | PINK_WOOL_SLAB | 0.001513 | 2.31e+04 | crafted |
| 321 | ORANGE_TULIP | 0.001506 | 2.32e+04 | drop block |
| 322 | PINK_CUSHION | 0.001503 | 2.33e+04 | crafted |
| 323 | BOOK | 0.001488 | 2.35e+04 | drop |
| 324 | PINK_WOOL_STAIRS | 0.001477 | 2.37e+04 | crafted |
| 325 | FARMLAND | 0.001461 | 2.39e+04 | block |
| 326 | BLACK_HARNESS | 0.001454 | 2.4e+04 | crafted |
| 327 | NETHER_BRICK_FENCE | 0.001427 | 2.45e+04 | crafted |
| 328 | BROWN_BANNER | 0.001388 | 2.52e+04 | crafted |
| 329 | PINK_WOOL | 0.00138 | 2.53e+04 | crafted |
| 330 | PUMPKIN | 0.00132 | 2.65e+04 | drop block |
| 331 | COPPER_HELMET | 0.001318 | 2.65e+04 | crafted |
| 332 | LANTERN | 0.001287 | 2.72e+04 | crafted |
| 333 | SADDLE | 0.001266 | 2.76e+04 | crafted |
| 334 | LILY_OF_THE_VALLEY | 0.001204 | 2.91e+04 | drop block |
| 335 | RED_WOOL_STAIRS | 0.001201 | 2.91e+04 | crafted |
| 336 | RAW_IRON | 0.00119 | 2.94e+04 | drop |
| 337 | IRON_INGOT | 0.00119 | 2.94e+04 | crafted |
| 338 | IRON_NUGGET | 0.00119 | 2.94e+04 | crafted |
| 339 | STONECUTTER | 0.001184 | 2.95e+04 | crafted |
| 340 | LODESTONE | 0.001179 | 2.97e+04 | crafted |
| 341 | COPPER_BARS | 0.001177 | 2.97e+04 | crafted |
| 342 | COPPER_DOOR | 0.001177 | 2.97e+04 | crafted |
| 343 | GLOW_LICHEN | 0.001169 | 2.99e+04 | block |
| 344 | PINK_HARNESS | 0.001139 | 3.07e+04 | crafted |
| 345 | RED_CUSHION | 0.00113 | 3.1e+04 | crafted |
| 346 | RED_WOOL_SLAB | 0.00113 | 3.1e+04 | crafted |
| 347 | LIGHT_GRAY_BANNER | 0.00112 | 3.12e+04 | crafted |
| 348 | ACACIA_PLANKS | 0.001114 | 3.14e+04 | drop block |
| 349 | DEAD_HORN_CORAL_BLOCK | 0.001113 | 3.14e+04 | drop |
| 350 | BROWN_BED | 0.001108 | 3.15e+04 | crafted |
| 351 | OBSIDIAN | 0.001108 | 3.16e+04 | drop block |
| 352 | COPPER_LEGGINGS | 0.001079 | 3.24e+04 | crafted |
| 353 | IRON_SHOVEL | 0.001077 | 3.25e+04 | crafted |
| 354 | IRON_SPEAR | 0.001077 | 3.25e+04 | crafted |
| 355 | DEAD_FIRE_CORAL_BLOCK | 0.001074 | 3.25e+04 | drop |
| 356 | MOSS_CARPET | 0.001069 | 3.27e+04 | drop block |
| 357 | RED_WOOL | 0.001023 | 3.42e+04 | drop block |
| 358 | COPPER_CHESTPLATE | 0.001008 | 3.47e+04 | crafted |
| 359 | TERRACOTTA | 0.001002 | 3.49e+04 | crafted |
| 360 | BRICKS | 0.001002 | 3.49e+04 | crafted |
| 361 | DECORATED_POT | 0.001002 | 3.49e+04 | crafted |
| 362 | BRICK_SLAB | 0.001002 | 3.49e+04 | crafted |
| 363 | BRICK_STAIRS | 0.001002 | 3.49e+04 | crafted |
| 364 | BRICK_WALL | 0.001002 | 3.49e+04 | crafted |
| 365 | PINK_BED | 0.0009814 | 3.56e+04 | crafted |
| 366 | LIGHT_GRAY_HARNESS | 0.0009748 | 3.59e+04 | crafted |
| 367 | RAW_COPPER_BLOCK | 0.0009542 | 3.66e+04 | crafted |
| 368 | COPPER_BLOCK | 0.0009542 | 3.66e+04 | crafted |
| 369 | COPPER_GRATE | 0.0009542 | 3.66e+04 | crafted |
| 370 | CUT_COPPER | 0.0009542 | 3.66e+04 | crafted |
| 371 | CHISELED_COPPER | 0.0009542 | 3.66e+04 | crafted |
| 372 | CUT_COPPER_SLAB | 0.0009542 | 3.66e+04 | crafted |
| 373 | CUT_COPPER_STAIRS | 0.0009542 | 3.66e+04 | crafted |
| 374 | DARK_OAK_SAPLING | 0.0009509 | 3.68e+04 | drop block |
| 375 | PINK_DYE | 0.0008975 | 3.9e+04 | crafted |
| 376 | BRAIN_CORAL_BLOCK | 0.0008935 | 3.91e+04 | block |
| 377 | RED_BED | 0.0008911 | 3.92e+04 | crafted |
| 378 | PINK_CONCRETE_POWDER | 0.0008676 | 4.03e+04 | crafted |
| 379 | SHIELD | 0.0008497 | 4.12e+04 | crafted |
| 380 | TRIPWIRE_HOOK | 0.0008469 | 4.13e+04 | crafted |
| 381 | ALLIUM | 0.0008311 | 4.21e+04 | drop block |
| 382 | WHEAT | 0.0008213 | 4.26e+04 | block |
| 383 | MOSSY_STONE_BRICK_STAIRS | 0.0008169 | 4.28e+04 | drop block |
| 384 | GREEN_CONCRETE_POWDER | 0.0008042 | 4.35e+04 | crafted |
| 385 | GREEN_DYE | 0.0007856 | 4.45e+04 | crafted |
| 386 | GREEN_STAINED_GLASS_PANE | 0.0007825 | 4.47e+04 | crafted |
| 387 | GREEN_STAINED_GLASS | 0.0007812 | 4.48e+04 | crafted |
| 388 | PINK_STAINED_GLASS_PANE | 0.00078 | 4.48e+04 | crafted |
| 389 | TUBE_CORAL_BLOCK | 0.0007765 | 4.5e+04 | block |
| 390 | COPPER_CHEST | 0.0007702 | 4.54e+04 | crafted |
| 391 | FIREFLY_BUSH | 0.0007602 | 4.6e+04 | drop block |
| 392 | ORANGE_CARPET | 0.000757 | 4.62e+04 | crafted |
| 393 | LIGHT_GRAY_DYE | 0.0007556 | 4.63e+04 | crafted |
| 394 | PINK_STAINED_GLASS | 0.00075 | 4.66e+04 | crafted |
| 395 | TRAPPED_CHEST | 0.0007319 | 4.78e+04 | crafted |
| 396 | ORANGE_WOOL_STAIRS | 0.0007231 | 4.84e+04 | crafted |
| 397 | RED_HARNESS | 0.0007173 | 4.87e+04 | crafted |
| 398 | LIGHT_GRAY_CONCRETE_POWDER | 0.0007007 | 4.99e+04 | crafted |
| 399 | BUBBLE_CORAL_BLOCK | 0.0006874 | 5.09e+04 | block |
| 400 | RED_DYE | 0.0006684 | 5.23e+04 | crafted |
| 401 | PAPER | 0.0006628 | 5.28e+04 | crafted |
| 402 | ORANGE_WOOL_SLAB | 0.0006489 | 5.39e+04 | crafted |
| 403 | HEAVY_WEIGHTED_PRESSURE_PLATE | 0.0006432 | 5.44e+04 | crafted |
| 404 | SHEARS | 0.0006432 | 5.44e+04 | crafted |
| 405 | IRON_CHAIN | 0.0006432 | 5.44e+04 | crafted |
| 406 | ACACIA_BUTTON | 0.0006405 | 5.46e+04 | crafted |
| 407 | LIGHT_GRAY_STAINED_GLASS_PANE | 0.0006319 | 5.53e+04 | crafted |
| 408 | ACACIA_PRESSURE_PLATE | 0.0006238 | 5.61e+04 | crafted |
| 409 | YELLOW_HARNESS | 0.0006225 | 5.62e+04 | crafted |
| 410 | IRON_SWORD | 0.0006191 | 5.65e+04 | crafted |
| 411 | LECTERN | 0.0006191 | 5.65e+04 | crafted |
| 412 | ACACIA_SLAB | 0.0006183 | 5.65e+04 | crafted |
| 413 | GREEN_CARPET | 0.0006091 | 5.74e+04 | drop block |
| 414 | IRON_HOE | 0.0006082 | 5.75e+04 | crafted |
| 415 | LIGHT_GRAY_STAINED_GLASS | 0.0006058 | 5.77e+04 | crafted |
| 416 | ORANGE_CUSHION | 0.00058 | 6.03e+04 | crafted |
| 417 | MOSSY_STONE_BRICKS | 0.0005763 | 6.07e+04 | drop block |
| 418 | RED_MUSHROOM | 0.0005755 | 6.08e+04 | drop block |
| 419 | MOSSY_STONE_BRICK_SLAB | 0.0005746 | 6.09e+04 | drop block |
| 420 | CACTUS_FLOWER | 0.0005743 | 6.09e+04 | drop block |
| 421 | YELLOW_CONCRETE_POWDER | 0.0005539 | 6.31e+04 | crafted |
| 422 | PUMPKIN_SEEDS | 0.0005256 | 6.65e+04 | crafted |
| 423 | ORANGE_BED | 0.0005245 | 6.67e+04 | crafted |
| 424 | RED_CONCRETE_POWDER | 0.0005091 | 6.87e+04 | crafted |
| 425 | HORN_CORAL_BLOCK | 0.0005042 | 6.94e+04 | block |
| 426 | YELLOW_STAINED_GLASS_PANE | 0.0004936 | 7.08e+04 | crafted |
| 427 | FIRE_CORAL_BLOCK | 0.0004865 | 7.19e+04 | block |
| 428 | ORANGE_WOOL | 0.0004851 | 7.21e+04 | crafted |
| 429 | BUBBLE_CORAL_WALL_FAN | 0.0004794 | 7.29e+04 | block |
| 430 | DIRT_PATH | 0.0004783 | 7.31e+04 | block |
| 431 | YELLOW_STAINED_GLASS | 0.0004781 | 7.31e+04 | crafted |
| 432 | RED_STAINED_GLASS_PANE | 0.0004667 | 7.49e+04 | crafted |
| 433 | NAME_TAG | 0.0004636 | 7.54e+04 | crafted |
| 434 | BUCKET | 0.0004625 | 7.56e+04 | crafted |
| 435 | RED_STAINED_GLASS | 0.0004493 | 7.78e+04 | crafted |
| 436 | IRON_AXE | 0.000444 | 7.87e+04 | crafted |
| 437 | IRON_PICKAXE | 0.000444 | 7.87e+04 | crafted |
| 438 | SMITHING_TABLE | 0.0004426 | 7.9e+04 | crafted |
| 439 | MOSSY_STONE_BRICK_WALL | 0.0004358 | 8.02e+04 | crafted |
| 440 | FIRE_CORAL_WALL_FAN | 0.0004112 | 8.5e+04 | block |
| 441 | HORN_CORAL_WALL_FAN | 0.0004046 | 8.64e+04 | block |
| 442 | BLUE_CARPET | 0.0004006 | 8.73e+04 | drop block |
| 443 | ORANGE_DYE | 0.0003813 | 9.17e+04 | crafted |
| 444 | BRAIN_CORAL_WALL_FAN | 0.0003793 | 9.22e+04 | block |
| 445 | IRON_DOOR | 0.0003758 | 9.3e+04 | drop block |
| 446 | IRON_BOOTS | 0.0003734 | 9.36e+04 | crafted |
| 447 | IRON_TRAPDOOR | 0.0003734 | 9.36e+04 | crafted |
| 448 | BEE_NEST | 0.0003667 | 9.54e+04 | block |
| 449 | SUSPICIOUS_STEW | 0.0003481 | 1e+05 | crafted |
| 450 | TUBE_CORAL_WALL_FAN | 0.0003429 | 1.02e+05 | block |
| 451 | ACACIA_BOAT | 0.0003364 | 1.04e+05 | crafted |
| 452 | ACACIA_FENCE_GATE | 0.000336 | 1.04e+05 | crafted |
| 453 | ACACIA_DOOR | 0.0003353 | 1.04e+05 | crafted |
| 454 | ACACIA_TRAPDOOR | 0.0003353 | 1.04e+05 | crafted |
| 455 | ACACIA_FENCE | 0.0003338 | 1.05e+05 | crafted |
| 456 | CAULDRON | 0.000322 | 1.09e+05 | drop block |
| 457 | IRON_HELMET | 0.0003208 | 1.09e+05 | crafted |
| 458 | MINECART | 0.0003208 | 1.09e+05 | crafted |
| 459 | FURNACE_MINECART | 0.0003201 | 1.09e+05 | crafted |
| 460 | BLAST_FURNACE | 0.0003199 | 1.09e+05 | crafted |
| 461 | JUNGLE_PLANKS | 0.0003185 | 1.1e+05 | crafted |
| 462 | JUNGLE_PRESSURE_PLATE | 0.0003185 | 1.1e+05 | crafted |
| 463 | JUNGLE_SLAB | 0.0003185 | 1.1e+05 | crafted |
| 464 | JUNGLE_BUTTON | 0.0003185 | 1.1e+05 | crafted |
| 465 | IRON_BARS | 0.0003162 | 1.11e+05 | drop block |
| 466 | GRAY_DYE | 0.0003139 | 1.11e+05 | crafted |
| 467 | FIELD_MASONED_BANNER_PATTERN | 0.0003061 | 1.14e+05 | crafted |
| 468 | SEA_PICKLE | 0.0002909 | 1.2e+05 | drop block |
| 469 | RAIL | 0.0002814 | 1.24e+05 | crafted |
| 470 | LILY_PAD | 0.0002793 | 1.25e+05 | drop block |
| 471 | CHEST_MINECART | 0.0002741 | 1.28e+05 | crafted |
| 472 | HOPPER | 0.0002741 | 1.28e+05 | crafted |
| 473 | JUNGLE_FENCE | 0.0002715 | 1.29e+05 | crafted |
| 474 | AZALEA | 0.0002699 | 1.3e+05 | drop block |
| 475 | PINK_BANNER | 0.0002656 | 1.32e+05 | crafted |
| 476 | ORANGE_HARNESS | 0.0002632 | 1.33e+05 | crafted |
| 477 | IRON_LEGGINGS | 0.0002627 | 1.33e+05 | crafted |
| 478 | LAVA | 0.0002527 | 1.38e+05 | block |
| 479 | CORNFLOWER | 0.0002507 | 1.39e+05 | drop block |
| 480 | IRON_CHESTPLATE | 0.0002453 | 1.43e+05 | crafted |
| 481 | ACACIA_SIGN | 0.0002402 | 1.46e+05 | crafted |
| 482 | CRYING_OBSIDIAN | 0.0002397 | 1.46e+05 | drop block |
| 483 | JUNGLE_FENCE_GATE | 0.0002381 | 1.47e+05 | crafted |
| 484 | RAW_IRON_BLOCK | 0.0002323 | 1.51e+05 | crafted |
| 485 | IRON_BLOCK | 0.0002323 | 1.51e+05 | crafted |
| 486 | CARTOGRAPHY_TABLE | 0.0002214 | 1.58e+05 | crafted |
| 487 | ORANGE_CONCRETE_POWDER | 0.0002135 | 1.64e+05 | crafted |
| 488 | RED_BANNER | 0.0002067 | 1.69e+05 | crafted |
| 489 | ORANGE_STAINED_GLASS_PANE | 0.0002039 | 1.71e+05 | crafted |
| 490 | ORANGE_STAINED_GLASS | 0.0001994 | 1.75e+05 | crafted |
| 491 | HOPPER_MINECART | 0.0001981 | 1.77e+05 | crafted |
| 492 | ACACIA_CHEST_BOAT | 0.0001938 | 1.8e+05 | crafted |
| 493 | ACACIA_WOOD | 0.0001897 | 1.84e+05 | crafted |
| 494 | MELON_STEM | 0.0001876 | 1.86e+05 | block |
| 495 | BLUE_CONCRETE_POWDER | 0.0001847 | 1.89e+05 | crafted |
| 496 | ANVIL | 0.0001796 | 1.95e+05 | crafted |
| 497 | BLUE_STAINED_GLASS_PANE | 0.0001769 | 1.98e+05 | crafted |
| 498 | YELLOW_TERRACOTTA | 0.000176 | 1.99e+05 | crafted |
| 499 | YELLOW_GLAZED_TERRACOTTA | 0.000176 | 1.99e+05 | crafted |
| 500 | BLUE_STAINED_GLASS | 0.0001758 | 1.99e+05 | crafted |
| 501 | BLUE_DYE | 0.0001749 | 2e+05 | crafted |
| 502 | LAPIS_LAZULI | 0.0001748 | 2e+05 | drop |
| 503 | LIGHT_BLUE_CARPET | 0.0001725 | 2.03e+05 | drop block |
| 504 | JUNGLE_STAIRS | 0.0001722 | 2.03e+05 | crafted |
| 505 | JUNGLE_TRAPDOOR | 0.0001722 | 2.03e+05 | crafted |
| 506 | JUNGLE_BOAT | 0.0001722 | 2.03e+05 | crafted |
| 507 | JUNGLE_DOOR | 0.0001722 | 2.03e+05 | crafted |
| 508 | JUNGLE_SIGN | 0.0001659 | 2.11e+05 | crafted |
| 509 | GREEN_TERRACOTTA | 0.0001604 | 2.18e+05 | crafted |
| 510 | GREEN_GLAZED_TERRACOTTA | 0.0001604 | 2.18e+05 | crafted |
| 511 | PINK_TERRACOTTA | 0.0001552 | 2.25e+05 | crafted |
| 512 | PINK_GLAZED_TERRACOTTA | 0.0001552 | 2.25e+05 | crafted |
| 513 | DEEPSLATE | 0.0001548 | 2.26e+05 | block |
| 514 | COCOA | 0.0001504 | 2.32e+05 | block |
| 515 | RED_TERRACOTTA | 0.000145 | 2.41e+05 | crafted |
| 516 | RED_GLAZED_TERRACOTTA | 0.000145 | 2.41e+05 | crafted |
| 517 | BLUE_TERRACOTTA | 0.0001444 | 2.42e+05 | crafted |
| 518 | BLUE_GLAZED_TERRACOTTA | 0.0001444 | 2.42e+05 | crafted |
| 519 | LIGHT_GRAY_TERRACOTTA | 0.000138 | 2.53e+05 | crafted |
| 520 | LIGHT_GRAY_GLAZED_TERRACOTTA | 0.000138 | 2.53e+05 | crafted |
| 521 | BLUE_WOOL_STAIRS | 0.0001377 | 2.54e+05 | crafted |
| 522 | FLOWERING_AZALEA | 0.0001368 | 2.56e+05 | drop block |
| 523 | MAGENTA_WOOL_STAIRS | 0.0001224 | 2.86e+05 | crafted |
| 524 | POTTED_RED_TULIP | 0.0001219 | 2.87e+05 | block |
| 525 | MELON_SEEDS | 0.0001216 | 2.88e+05 | drop |
| 526 | HORN_CORAL_FAN | 0.0001197 | 2.92e+05 | block |
| 527 | BAMBOO | 0.0001109 | 3.15e+05 | drop block |
| 528 | ORANGE_TERRACOTTA | 0.0001109 | 3.15e+05 | crafted |
| 529 | ORANGE_GLAZED_TERRACOTTA | 0.0001109 | 3.15e+05 | crafted |
| 530 | POTTED_OXEYE_DAISY | 0.0001082 | 3.23e+05 | block |
| 531 | MAGENTA_WOOL_SLAB | 0.0001078 | 3.24e+05 | crafted |
| 532 | MAGENTA_CUSHION | 0.0001076 | 3.25e+05 | crafted |
| 533 | PURPLE_WOOL_STAIRS | 0.0001053 | 3.32e+05 | crafted |
| 534 | ORANGE_BANNER | 0.0001048 | 3.34e+05 | crafted |
| 535 | BLUE_WOOL_SLAB | 0.000103 | 3.39e+05 | crafted |
| 536 | BLUE_CUSHION | 0.000103 | 3.39e+05 | crafted |
| 537 | WALL_TORCH | 0.000101 | 3.46e+05 | block |
| 538 | JUNGLE_WOOD | 9.995e-05 | 3.5e+05 | crafted |
| 539 | MAGENTA_BED | 9.982e-05 | 3.5e+05 | crafted |
| 540 | MAGENTA_CARPET | 9.92e-05 | 3.52e+05 | crafted |
| 541 | JUNGLE_CHEST_BOAT | 9.359e-05 | 3.74e+05 | crafted |
| 542 | BLUE_BED | 8.99e-05 | 3.89e+05 | crafted |
| 543 | PURPLE_WOOL_SLAB | 8.883e-05 | 3.94e+05 | crafted |
| 544 | MAGENTA_HARNESS | 8.778e-05 | 3.98e+05 | crafted |
| 545 | POTTED_WHITE_TULIP | 8.75e-05 | 4e+05 | block |
| 546 | MAGENTA_WOOL | 8.718e-05 | 4.01e+05 | crafted |
| 547 | PURPLE_CARPET | 8.073e-05 | 4.33e+05 | crafted |
| 548 | LIME_CONCRETE_POWDER | 7.956e-05 | 4.39e+05 | crafted |
| 549 | FIRE_CORAL | 7.946e-05 | 4.4e+05 | block |
| 550 | CYAN_CONCRETE_POWDER | 7.939e-05 | 4.4e+05 | crafted |
| 551 | LIME_DYE | 7.859e-05 | 4.45e+05 | crafted |
| 552 | LIME_STAINED_GLASS_PANE | 7.856e-05 | 4.45e+05 | crafted |
| 553 | LIME_STAINED_GLASS | 7.854e-05 | 4.45e+05 | crafted |
| 554 | PURPLE_BED | 7.798e-05 | 4.48e+05 | crafted |
| 555 | CYAN_DYE | 7.679e-05 | 4.55e+05 | crafted |
| 556 | CYAN_STAINED_GLASS_PANE | 7.677e-05 | 4.55e+05 | crafted |
| 557 | CYAN_STAINED_GLASS | 7.676e-05 | 4.56e+05 | crafted |
| 558 | PURPLE_HARNESS | 7.572e-05 | 4.62e+05 | crafted |
| 559 | BRAIN_CORAL | 7.344e-05 | 4.76e+05 | block |
| 560 | MAGENTA_CONCRETE_POWDER | 7.238e-05 | 4.83e+05 | crafted |
| 561 | PURPLE_WOOL | 7.124e-05 | 4.91e+05 | crafted |
| 562 | BUBBLE_CORAL_FAN | 7.113e-05 | 4.92e+05 | block |
| 563 | MAGENTA_STAINED_GLASS_PANE | 7.09e-05 | 4.93e+05 | crafted |
| 564 | BIG_DRIPLEAF | 7.084e-05 | 4.94e+05 | drop block |
| 565 | CREAKING_HEART | 7.03e-05 | 4.97e+05 | block |
| 566 | MAGENTA_STAINED_GLASS | 6.965e-05 | 5.02e+05 | crafted |
| 567 | MAGENTA_DYE | 6.96e-05 | 5.02e+05 | crafted |
| 568 | BLUE_HARNESS | 6.892e-05 | 5.07e+05 | crafted |
| 569 | WHITE_DYE | 6.864e-05 | 5.09e+05 | crafted |
| 570 | POTTED_DANDELION | 6.312e-05 | 5.54e+05 | block |
| 571 | PURPLE_CONCRETE_POWDER | 6.274e-05 | 5.57e+05 | crafted |
| 572 | POTTED_POPPY | 6.271e-05 | 5.58e+05 | block |
| 573 | PURPLE_STAINED_GLASS_PANE | 6.108e-05 | 5.72e+05 | crafted |
| 574 | PURPLE_STAINED_GLASS | 6.056e-05 | 5.77e+05 | crafted |
| 575 | BLUE_WOOL | 6.015e-05 | 5.81e+05 | crafted |
| 576 | PURPLE_DYE | 6.01e-05 | 5.82e+05 | crafted |
| 577 | PURPLE_TERRACOTTA | 5.967e-05 | 5.86e+05 | crafted |
| 578 | PURPLE_GLAZED_TERRACOTTA | 5.967e-05 | 5.86e+05 | crafted |
| 579 | TUBE_CORAL_FAN | 5.896e-05 | 5.93e+05 | block |
| 580 | PURPLE_CUSHION | 5.869e-05 | 5.96e+05 | crafted |
| 581 | CYAN_TERRACOTTA | 5.752e-05 | 6.08e+05 | crafted |
| 582 | CYAN_GLAZED_TERRACOTTA | 5.752e-05 | 6.08e+05 | crafted |
| 583 | LIME_TERRACOTTA | 5.67e-05 | 6.17e+05 | crafted |
| 584 | LIME_GLAZED_TERRACOTTA | 5.67e-05 | 6.17e+05 | crafted |
| 585 | BUBBLE_CORAL | 5.615e-05 | 6.23e+05 | block |
| 586 | BROWN_CONCRETE_POWDER | 5.426e-05 | 6.44e+05 | crafted |
| 587 | COCOA_BEANS | 5.374e-05 | 6.51e+05 | drop |
| 588 | BROWN_DYE | 5.374e-05 | 6.51e+05 | crafted |
| 589 | BROWN_STAINED_GLASS_PANE | 5.372e-05 | 6.51e+05 | crafted |
| 590 | BROWN_STAINED_GLASS | 5.372e-05 | 6.51e+05 | crafted |
| 591 | TUBE_CORAL | 5.195e-05 | 6.73e+05 | block |
| 592 | POTTED_AZURE_BLUET | 5.195e-05 | 6.73e+05 | block |
| 593 | BRAIN_CORAL_FAN | 5.01e-05 | 6.98e+05 | block |
| 594 | HORN_CORAL | 5.002e-05 | 6.99e+05 | block |
| 595 | GREEN_WOOL | 4.952e-05 | 7.06e+05 | drop block |
| 596 | FIRE_CORAL_FAN | 4.343e-05 | 8.05e+05 | block |
| 597 | BROWN_TERRACOTTA | 4.258e-05 | 8.21e+05 | crafted |
| 598 | BROWN_GLAZED_TERRACOTTA | 4.258e-05 | 8.21e+05 | crafted |
| 599 | LAPIS_BLOCK | 4.152e-05 | 8.42e+05 | drop block |
| 600 | SWEET_BERRIES | 3.867e-05 | 9.04e+05 | drop |
| 601 | MAGENTA_TERRACOTTA | 3.811e-05 | 9.17e+05 | crafted |
| 602 | MAGENTA_GLAZED_TERRACOTTA | 3.811e-05 | 9.17e+05 | crafted |
| 603 | FLOWER_BANNER_PATTERN | 3.356e-05 | 1.04e+06 | crafted |
| 604 | REDSTONE_ORE | 3.326e-05 | 1.05e+06 | block |
| 605 | SWEET_BERRY_BUSH | 2.931e-05 | 1.19e+06 | block |
| 606 | RESIN_CLUMP | 2.122e-05 | 1.65e+06 | drop |
| 607 | RESIN_BRICK | 2.122e-05 | 1.65e+06 | crafted |
| 608 | LIGHT_BLUE_WOOL_STAIRS | 2.075e-05 | 1.69e+06 | crafted |
| 609 | MAGENTA_BANNER | 2.058e-05 | 1.7e+06 | crafted |
| 610 | WHITE_TERRACOTTA | 2.046e-05 | 1.71e+06 | crafted |
| 611 | WHITE_GLAZED_TERRACOTTA | 2.046e-05 | 1.71e+06 | crafted |
| 612 | WHITE_CONCRETE_POWDER | 2.041e-05 | 1.71e+06 | crafted |
| 613 | WHITE_STAINED_GLASS_PANE | 2.016e-05 | 1.73e+06 | crafted |
| 614 | WHITE_STAINED_GLASS | 1.997e-05 | 1.75e+06 | crafted |
| 615 | LIGHT_BLUE_WOOL_SLAB | 1.979e-05 | 1.77e+06 | crafted |
| 616 | LIGHT_BLUE_BED | 1.939e-05 | 1.8e+06 | crafted |
| 617 | LIGHT_BLUE_WOOL | 1.932e-05 | 1.81e+06 | crafted |
| 618 | LIGHT_BLUE_HARNESS | 1.925e-05 | 1.82e+06 | crafted |
| 619 | CAVE_VINES_PLANT | 1.813e-05 | 1.93e+06 | block |
| 620 | COBBLED_DEEPSLATE | 1.706e-05 | 2.05e+06 | drop |
| 621 | DEEPSLATE_TILE_SLAB | 1.706e-05 | 2.05e+06 | crafted |
| 622 | DEEPSLATE_TILE_STAIRS | 1.706e-05 | 2.05e+06 | crafted |
| 623 | DEEPSLATE_TILE_WALL | 1.706e-05 | 2.05e+06 | crafted |
| 624 | BIG_DRIPLEAF_STEM | 1.497e-05 | 2.34e+06 | block |
| 625 | BLUE_BANNER | 1.408e-05 | 2.48e+06 | crafted |
| 626 | CAVE_VINES | 1.388e-05 | 2.52e+06 | block |
| 627 | PURPLE_BANNER | 1.335e-05 | 2.62e+06 | crafted |
| 628 | LIGHT_BLUE_CUSHION | 1.289e-05 | 2.71e+06 | crafted |
| 629 | GRAY_WALL_BANNER | 1.216e-05 | 2.88e+06 | block |
| 630 | LIGHT_BLUE_GLAZED_TERRACOTTA | 1.158e-05 | 3.02e+06 | crafted |
| 631 | LIGHT_BLUE_TERRACOTTA | 1.158e-05 | 3.02e+06 | crafted |
| 632 | LIGHT_BLUE_CONCRETE_POWDER | 1.059e-05 | 3.3e+06 | crafted |
| 633 | LIGHT_BLUE_STAINED_GLASS_PANE | 1.051e-05 | 3.33e+06 | crafted |
| 634 | LIGHT_BLUE_STAINED_GLASS | 1.045e-05 | 3.35e+06 | crafted |
| 635 | LIGHT_BLUE_DYE | 1.029e-05 | 3.4e+06 | crafted |
| 636 | REDSTONE | 8.846e-06 | 3.95e+06 | drop |
| 637 | DROPPER | 8.843e-06 | 3.95e+06 | crafted |
| 638 | REDSTONE_TORCH | 8.811e-06 | 3.97e+06 | crafted |
| 639 | COMPASS | 8.308e-06 | 4.21e+06 | crafted |
| 640 | MAP | 8.293e-06 | 4.22e+06 | crafted |
| 641 | DETECTOR_RAIL | 8.155e-06 | 4.29e+06 | crafted |
| 642 | ACTIVATOR_RAIL | 8.077e-06 | 4.33e+06 | crafted |
| 643 | DIAMOND_ORE | 7.835e-06 | 4.46e+06 | block |
| 644 | RESIN_BRICKS | 6.658e-06 | 5.25e+06 | crafted |
| 645 | CHISELED_RESIN_BRICKS | 6.658e-06 | 5.25e+06 | crafted |
| 646 | RESIN_BRICK_SLAB | 6.658e-06 | 5.25e+06 | crafted |
| 647 | RESIN_BRICK_STAIRS | 6.658e-06 | 5.25e+06 | crafted |
| 648 | RESIN_BRICK_WALL | 6.658e-06 | 5.25e+06 | crafted |
| 649 | COBBLED_DEEPSLATE_SLAB | 6.633e-06 | 5.27e+06 | crafted |
| 650 | CHISELED_DEEPSLATE | 6.633e-06 | 5.27e+06 | crafted |
| 651 | PISTON | 6.357e-06 | 5.5e+06 | crafted |
| 652 | SPAWNER | 5.799e-06 | 6.03e+06 | block |
| 653 | POLISHED_DEEPSLATE_SLAB | 5.355e-06 | 6.53e+06 | crafted |
| 654 | DEEPSLATE_BRICKS | 5.355e-06 | 6.53e+06 | crafted |
| 655 | DEEPSLATE_TILES | 5.355e-06 | 6.53e+06 | crafted |
| 656 | CRACKED_DEEPSLATE_BRICKS | 5.355e-06 | 6.53e+06 | crafted |
| 657 | CRACKED_DEEPSLATE_TILES | 5.355e-06 | 6.53e+06 | crafted |
| 658 | DEEPSLATE_BRICK_SLAB | 5.355e-06 | 6.53e+06 | crafted |
| 659 | POLISHED_DEEPSLATE | 5.355e-06 | 6.53e+06 | crafted |
| 660 | LIGHT_BLUE_BANNER | 4.258e-06 | 8.21e+06 | crafted |
| 661 | NOTE_BLOCK | 4.21e-06 | 8.3e+06 | crafted |
| 662 | RESIN_BLOCK | 4.142e-06 | 8.44e+06 | crafted |
| 663 | COBBLED_DEEPSLATE_STAIRS | 4.109e-06 | 8.51e+06 | crafted |
| 664 | COBBLED_DEEPSLATE_WALL | 4.109e-06 | 8.51e+06 | crafted |
| 665 | BAMBOO_BLOCK | 4.012e-06 | 8.71e+06 | crafted |
| 666 | BAMBOO_PLANKS | 4.012e-06 | 8.71e+06 | crafted |
| 667 | BAMBOO_PRESSURE_PLATE | 4.012e-06 | 8.71e+06 | crafted |
| 668 | BAMBOO_BUTTON | 4.012e-06 | 8.71e+06 | crafted |
| 669 | BAMBOO_FENCE_GATE | 4.004e-06 | 8.73e+06 | crafted |
| 670 | POLISHED_DEEPSLATE_STAIRS | 3.518e-06 | 9.94e+06 | crafted |
| 671 | POLISHED_DEEPSLATE_WALL | 3.518e-06 | 9.94e+06 | crafted |
| 672 | DEEPSLATE_BRICK_STAIRS | 3.518e-06 | 9.94e+06 | crafted |
| 673 | DEEPSLATE_BRICK_WALL | 3.518e-06 | 9.94e+06 | crafted |
| 674 | REPEATER | 3.428e-06 | 1.02e+07 | crafted |
| 675 | CRAFTER | 3.31e-06 | 1.06e+07 | crafted |
| 676 | BAMBOO_SLAB | 3.258e-06 | 1.07e+07 | crafted |
| 677 | BAMBOO_MOSAIC | 3.258e-06 | 1.07e+07 | crafted |
| 678 | BAMBOO_FENCE | 3.253e-06 | 1.07e+07 | crafted |
| 679 | BAMBOO_RAFT | 3.122e-06 | 1.12e+07 | crafted |
| 680 | BAMBOO_STAIRS | 3.122e-06 | 1.12e+07 | crafted |
| 681 | BAMBOO_TRAPDOOR | 3.122e-06 | 1.12e+07 | crafted |
| 682 | BAMBOO_DOOR | 3.122e-06 | 1.12e+07 | crafted |
| 683 | BAMBOO_SIGN | 3.119e-06 | 1.12e+07 | crafted |
| 684 | BAMBOO_MOSAIC_SLAB | 3.084e-06 | 1.13e+07 | crafted |
| 685 | BAMBOO_MOSAIC_STAIRS | 3.083e-06 | 1.13e+07 | crafted |
| 686 | BAMBOO_CHEST_RAFT | 3.07e-06 | 1.14e+07 | crafted |
| 687 | DIAMOND | 2.585e-06 | 1.35e+07 | drop |
| 688 | DIAMOND_SHOVEL | 2.58e-06 | 1.36e+07 | crafted |
| 689 | DIAMOND_SPEAR | 2.58e-06 | 1.36e+07 | crafted |
| 690 | GREEN_BED | 2.111e-06 | 1.66e+07 | crafted |
| 691 | GREEN_WOOL_SLAB | 2.064e-06 | 1.69e+07 | crafted |
| 692 | GREEN_CUSHION | 2.064e-06 | 1.69e+07 | crafted |
| 693 | JUKEBOX | 1.811e-06 | 1.93e+07 | crafted |
| 694 | REDSTONE_BLOCK | 1.727e-06 | 2.02e+07 | crafted |
| 695 | DIAMOND_SWORD | 1.397e-06 | 2.5e+07 | crafted |
| 696 | DIAMOND_HOE | 1.396e-06 | 2.51e+07 | crafted |
| 697 | GREEN_WOOL_STAIRS | 1.279e-06 | 2.73e+07 | crafted |
| 698 | GREEN_BANNER | 1.27e-06 | 2.75e+07 | crafted |
| 699 | DIAMOND_AXE | 1.004e-06 | 3.48e+07 | crafted |
| 700 | DIAMOND_PICKAXE | 1.004e-06 | 3.48e+07 | crafted |
| 701 | DIAMOND_BOOTS | 8.113e-07 | 4.31e+07 | crafted |
| 702 | DIAMOND_HELMET | 6.971e-07 | 5.02e+07 | crafted |
| 703 | DIAMOND_LEGGINGS | 5.708e-07 | 6.13e+07 | crafted |
| 704 | DIAMOND_CHESTPLATE | 5.33e-07 | 6.56e+07 | crafted |
| 705 | DIAMOND_BLOCK | 5.047e-07 | 6.93e+07 | crafted |

## B. World spawn of the Desktop server

Sampled from the world spawn (tundra / frozen ocean).

| # | Item | Chance % | Times rarer than #1 | Source |
|---|---|---|---|---|
| 1 | DIRT | 31.34 | 1 | drop block |
| 2 | WATER | 13.94 | 2.25 | block |
| 3 | STONE | 11.48 | 2.73 | block |
| 4 | ICE | 6.317 | 4.96 | block |
| 5 | SAND | 3.46 | 9.06 | drop block |
| 6 | GRASS_BLOCK | 3.053 | 10.3 | block |
| 7 | SNOW | 2.068 | 15.2 | block |
| 8 | GRAVEL | 1.628 | 19.3 | drop block |
| 9 | COBBLESTONE | 1.529 | 20.5 | drop |
| 10 | STONE_BUTTON | 1.529 | 20.5 | crafted |
| 11 | CHISELED_STONE_BRICKS | 1.529 | 20.5 | crafted |
| 12 | STONE_BRICK_SLAB | 1.529 | 20.5 | crafted |
| 13 | STONE_BRICK_STAIRS | 1.529 | 20.5 | crafted |
| 14 | STONE_BRICK_WALL | 1.529 | 20.5 | crafted |
| 15 | SMOOTH_STONE | 1.529 | 20.5 | crafted |
| 16 | SMOOTH_STONE_SLAB | 1.529 | 20.5 | crafted |
| 17 | ANDESITE | 1.027 | 30.5 | drop block |
| 18 | GLASS | 0.9889 | 31.7 | crafted |
| 19 | STONE_PRESSURE_PLATE | 0.8266 | 37.9 | crafted |
| 20 | GRANITE | 0.6449 | 48.6 | drop block |
| 21 | SPRUCE_LEAVES | 0.6118 | 51.2 | block |
| 22 | COBBLESTONE_SLAB | 0.5945 | 52.7 | crafted |
| 23 | STONE_SLAB | 0.5945 | 52.7 | crafted |
| 24 | STONE_BRICKS | 0.4799 | 65.3 | crafted |
| 25 | CRACKED_STONE_BRICKS | 0.4799 | 65.3 | crafted |
| 26 | COARSE_DIRT | 0.4674 | 67 | drop block |
| 27 | GLASS_BOTTLE | 0.3844 | 81.5 | crafted |
| 28 | COBBLESTONE_STAIRS | 0.3683 | 85.1 | crafted |
| 29 | COBBLESTONE_WALL | 0.3683 | 85.1 | crafted |
| 30 | STONE_STAIRS | 0.3683 | 85.1 | crafted |
| 31 | DIORITE | 0.368 | 85.1 | drop block |
| 32 | SANDSTONE | 0.3179 | 98.6 | drop block |
| 33 | FURNACE | 0.3153 | 99.4 | crafted |
| 34 | SMOOTH_SANDSTONE | 0.3126 | 100 | crafted |
| 35 | GLASS_PANE | 0.2382 | 132 | crafted |
| 36 | COAL | 0.1919 | 163 | drop |
| 37 | SANDSTONE_SLAB | 0.1738 | 180 | crafted |
| 38 | SMOOTH_SANDSTONE_SLAB | 0.1738 | 180 | crafted |
| 39 | CHISELED_SANDSTONE | 0.1738 | 180 | crafted |
| 40 | CLAY | 0.1703 | 184 | block |
| 41 | ANDESITE_SLAB | 0.1629 | 192 | crafted |
| 42 | CUT_SANDSTONE | 0.1609 | 195 | crafted |
| 43 | CUT_SANDSTONE_SLAB | 0.1609 | 195 | crafted |
| 44 | SANDSTONE_STAIRS | 0.1519 | 206 | crafted |
| 45 | SANDSTONE_WALL | 0.1519 | 206 | crafted |
| 46 | SMOOTH_SANDSTONE_STAIRS | 0.1519 | 206 | crafted |
| 47 | POLISHED_ANDESITE | 0.1333 | 235 | crafted |
| 48 | POLISHED_ANDESITE_SLAB | 0.1333 | 235 | crafted |
| 49 | COAL_ORE | 0.1304 | 240 | block |
| 50 | FEATHER | 0.1246 | 251 | drop |
| 51 | SPRUCE_LOG | 0.1101 | 285 | drop block |
| 52 | RABBIT | 0.1078 | 291 | drop |
| 53 | COOKED_RABBIT | 0.1078 | 291 | crafted |
| 54 | ANDESITE_STAIRS | 0.1017 | 308 | crafted |
| 55 | ANDESITE_WALL | 0.1017 | 308 | crafted |
| 56 | GRANITE_SLAB | 0.1011 | 310 | crafted |
| 57 | CHICKEN | 0.09337 | 336 | drop |
| 58 | COOKED_CHICKEN | 0.09337 | 336 | crafted |
| 59 | POLISHED_ANDESITE_STAIRS | 0.08676 | 361 | crafted |
| 60 | POLISHED_GRANITE | 0.0816 | 384 | crafted |
| 61 | POLISHED_GRANITE_SLAB | 0.0816 | 384 | crafted |
| 62 | COPPER_ORE | 0.07338 | 427 | block |
| 63 | GRANITE_STAIRS | 0.06262 | 500 | crafted |
| 64 | GRANITE_WALL | 0.06262 | 500 | crafted |
| 65 | CLAY_BALL | 0.06024 | 520 | drop |
| 66 | BRICK | 0.06024 | 520 | crafted |
| 67 | POLISHED_GRANITE_STAIRS | 0.05362 | 584 | crafted |
| 68 | RABBIT_HIDE | 0.04453 | 704 | drop |
| 69 | COAL_BLOCK | 0.03747 | 836 | crafted |
| 70 | FERN | 0.03527 | 889 | block |
| 71 | BEEF | 0.03248 | 965 | drop |
| 72 | COOKED_BEEF | 0.03248 | 965 | crafted |
| 73 | SHORT_GRASS | 0.0279 | 1.12e+03 | block |
| 74 | LARGE_FERN | 0.02435 | 1.29e+03 | block |
| 75 | FLOWER_POT | 0.02342 | 1.34e+03 | crafted |
| 76 | DIORITE_SLAB | 0.02284 | 1.37e+03 | crafted |
| 77 | LEATHER | 0.02217 | 1.41e+03 | drop |
| 78 | TERRACOTTA | 0.0189 | 1.66e+03 | crafted |
| 79 | BRICKS | 0.0189 | 1.66e+03 | crafted |
| 80 | DECORATED_POT | 0.0189 | 1.66e+03 | crafted |
| 81 | BRICK_SLAB | 0.0189 | 1.66e+03 | crafted |
| 82 | BRICK_STAIRS | 0.0189 | 1.66e+03 | crafted |
| 83 | BRICK_WALL | 0.0189 | 1.66e+03 | crafted |
| 84 | POLISHED_DIORITE | 0.01844 | 1.7e+03 | crafted |
| 85 | POLISHED_DIORITE_SLAB | 0.01844 | 1.7e+03 | crafted |
| 86 | RAW_COPPER | 0.01498 | 2.09e+03 | drop |
| 87 | COPPER_INGOT | 0.01498 | 2.09e+03 | crafted |
| 88 | COPPER_NUGGET | 0.01498 | 2.09e+03 | crafted |
| 89 | DIORITE_STAIRS | 0.01415 | 2.21e+03 | crafted |
| 90 | DIORITE_WALL | 0.01415 | 2.21e+03 | crafted |
| 91 | TORCH | 0.0141 | 2.22e+03 | crafted |
| 92 | POLISHED_DIORITE_STAIRS | 0.01211 | 2.59e+03 | crafted |
| 93 | LEATHER_BOOTS | 0.009704 | 3.23e+03 | crafted |
| 94 | LEATHER_HELMET | 0.009085 | 3.45e+03 | crafted |
| 95 | BRUSH | 0.008986 | 3.49e+03 | crafted |
| 96 | LEATHER_HORSE_ARMOR | 0.008496 | 3.69e+03 | crafted |
| 97 | LEATHER_LEGGINGS | 0.008496 | 3.69e+03 | crafted |
| 98 | LEATHER_CHESTPLATE | 0.008345 | 3.75e+03 | crafted |
| 99 | COPPER_CHAIN | 0.008096 | 3.87e+03 | crafted |
| 100 | IRON_ORE | 0.00716 | 4.38e+03 | block |
| 101 | COPPER_TORCH | 0.006938 | 4.52e+03 | crafted |
| 102 | SPRUCE_PLANKS | 0.006136 | 5.11e+03 | drop block |
| 103 | ITEM_FRAME | 0.006123 | 5.12e+03 | crafted |
| 104 | CHARCOAL | 0.006024 | 5.2e+03 | crafted |
| 105 | SPRUCE_BUTTON | 0.006009 | 5.22e+03 | crafted |
| 106 | SPRUCE_PRESSURE_PLATE | 0.006001 | 5.22e+03 | crafted |
| 107 | STICK | 0.006001 | 5.22e+03 | crafted |
| 108 | STONE_SHOVEL | 0.006 | 5.22e+03 | crafted |
| 109 | STONE_SPEAR | 0.006 | 5.22e+03 | crafted |
| 110 | LEVER | 0.006 | 5.22e+03 | crafted |
| 111 | BOWL | 0.005999 | 5.22e+03 | crafted |
| 112 | SPRUCE_SLAB | 0.005999 | 5.22e+03 | crafted |
| 113 | STONE_HOE | 0.005999 | 5.22e+03 | crafted |
| 114 | STONE_SWORD | 0.005999 | 5.22e+03 | crafted |
| 115 | CRAFTING_TABLE | 0.005998 | 5.22e+03 | crafted |
| 116 | STONE_AXE | 0.005997 | 5.23e+03 | crafted |
| 117 | STONE_PICKAXE | 0.005997 | 5.23e+03 | crafted |
| 118 | LIGHTNING_ROD | 0.005822 | 5.38e+03 | crafted |
| 119 | COPPER_SHOVEL | 0.005215 | 6.01e+03 | crafted |
| 120 | COPPER_SPEAR | 0.005215 | 6.01e+03 | crafted |
| 121 | COPPER_LANTERN | 0.004907 | 6.39e+03 | crafted |
| 122 | COPPER_BOOTS | 0.0047 | 6.67e+03 | crafted |
| 123 | COPPER_TRAPDOOR | 0.0047 | 6.67e+03 | crafted |
| 124 | LAPIS_ORE | 0.004567 | 6.86e+03 | block |
| 125 | FIREFLY_BUSH | 0.004242 | 7.39e+03 | drop block |
| 126 | COPPER_HOE | 0.00414 | 7.57e+03 | crafted |
| 127 | COPPER_SWORD | 0.00414 | 7.57e+03 | crafted |
| 128 | COPPER_HELMET | 0.004038 | 7.76e+03 | crafted |
| 129 | SPRUCE_FENCE | 0.003655 | 8.57e+03 | drop block |
| 130 | COPPER_BARS | 0.003607 | 8.69e+03 | crafted |
| 131 | COPPER_DOOR | 0.003607 | 8.69e+03 | crafted |
| 132 | COPPER_AXE | 0.003485 | 8.99e+03 | crafted |
| 133 | COPPER_PICKAXE | 0.003485 | 8.99e+03 | crafted |
| 134 | SPRUCE_STAIRS | 0.003375 | 9.29e+03 | drop block |
| 135 | COPPER_LEGGINGS | 0.003307 | 9.48e+03 | crafted |
| 136 | WOODEN_SHOVEL | 0.003274 | 9.57e+03 | crafted |
| 137 | WOODEN_SPEAR | 0.003274 | 9.57e+03 | crafted |
| 138 | WOODEN_HOE | 0.003272 | 9.58e+03 | crafted |
| 139 | WOODEN_SWORD | 0.003272 | 9.58e+03 | crafted |
| 140 | WOODEN_AXE | 0.003272 | 9.58e+03 | crafted |
| 141 | WOODEN_PICKAXE | 0.003272 | 9.58e+03 | crafted |
| 142 | GRINDSTONE | 0.003271 | 9.58e+03 | crafted |
| 143 | SPRUCE_DOOR | 0.003269 | 9.59e+03 | drop block |
| 144 | LADDER | 0.003244 | 9.66e+03 | crafted |
| 145 | ARMOR_STAND | 0.003244 | 9.66e+03 | crafted |
| 146 | SPRUCE_BOAT | 0.003244 | 9.66e+03 | crafted |
| 147 | SPRUCE_TRAPDOOR | 0.003243 | 9.66e+03 | crafted |
| 148 | COMPOSTER | 0.003243 | 9.66e+03 | crafted |
| 149 | CHEST | 0.003243 | 9.66e+03 | crafted |
| 150 | SPRUCE_FENCE_GATE | 0.003242 | 9.67e+03 | crafted |
| 151 | COPPER_CHESTPLATE | 0.003088 | 1.01e+04 | crafted |
| 152 | RAW_COPPER_BLOCK | 0.002924 | 1.07e+04 | crafted |
| 153 | COPPER_BLOCK | 0.002924 | 1.07e+04 | crafted |
| 154 | COPPER_GRATE | 0.002924 | 1.07e+04 | crafted |
| 155 | CUT_COPPER | 0.002924 | 1.07e+04 | crafted |
| 156 | CHISELED_COPPER | 0.002924 | 1.07e+04 | crafted |
| 157 | CUT_COPPER_SLAB | 0.002924 | 1.07e+04 | crafted |
| 158 | CUT_COPPER_STAIRS | 0.002924 | 1.07e+04 | crafted |
| 159 | CAMPFIRE | 0.002891 | 1.08e+04 | crafted |
| 160 | CHISELED_BOOKSHELF | 0.002347 | 1.34e+04 | crafted |
| 161 | BARREL | 0.002347 | 1.34e+04 | crafted |
| 162 | SPRUCE_SIGN | 0.00233 | 1.34e+04 | crafted |
| 163 | MAGMA_BLOCK | 0.002269 | 1.38e+04 | drop block |
| 164 | DIRT_PATH | 0.001997 | 1.57e+04 | block |
| 165 | SMOKER | 0.00189 | 1.66e+04 | crafted |
| 166 | SPRUCE_CHEST_BOAT | 0.001881 | 1.67e+04 | crafted |
| 167 | SPRUCE_WOOD | 0.001874 | 1.67e+04 | crafted |
| 168 | COPPER_CHEST | 0.001863 | 1.68e+04 | crafted |
| 169 | BUBBLE_COLUMN | 0.001446 | 2.17e+04 | block |
| 170 | POPPY | 0.001272 | 2.46e+04 | drop block |
| 171 | BLACK_WOOL | 0.001142 | 2.74e+04 | drop |
| 172 | PAINTING | 0.001127 | 2.78e+04 | crafted |
| 173 | BLUE_CONCRETE_POWDER | 0.001105 | 2.84e+04 | crafted |
| 174 | BLACK_HARNESS | 0.001101 | 2.85e+04 | crafted |
| 175 | BROWN_MUSHROOM | 0.001097 | 2.86e+04 | drop block |
| 176 | LAPIS_LAZULI | 0.001095 | 2.86e+04 | drop |
| 177 | BLUE_DYE | 0.001095 | 2.86e+04 | crafted |
| 178 | BLUE_STAINED_GLASS_PANE | 0.001093 | 2.87e+04 | crafted |
| 179 | BLUE_STAINED_GLASS | 0.00109 | 2.88e+04 | crafted |
| 180 | DANDELION | 0.001059 | 2.96e+04 | drop block |
| 181 | BLUE_TERRACOTTA | 0.0009926 | 3.16e+04 | crafted |
| 182 | BLUE_GLAZED_TERRACOTTA | 0.0009926 | 3.16e+04 | crafted |
| 183 | SADDLE | 0.0009198 | 3.41e+04 | crafted |
| 184 | LANTERN | 0.0007483 | 4.19e+04 | drop block |
| 185 | RAW_IRON | 0.0006886 | 4.55e+04 | drop |
| 186 | IRON_INGOT | 0.0006886 | 4.55e+04 | crafted |
| 187 | IRON_NUGGET | 0.0006886 | 4.55e+04 | crafted |
| 188 | STONECUTTER | 0.0006879 | 4.56e+04 | crafted |
| 189 | LODESTONE | 0.0006873 | 4.56e+04 | crafted |
| 190 | BLUE_HARNESS | 0.0006726 | 4.66e+04 | crafted |
| 191 | BLUE_WOOL | 0.0006555 | 4.78e+04 | crafted |
| 192 | IRON_SHOVEL | 0.0006235 | 5.03e+04 | crafted |
| 193 | IRON_SPEAR | 0.0006235 | 5.03e+04 | crafted |
| 194 | BLACK_CARPET | 0.0006172 | 5.08e+04 | crafted |
| 195 | TRIPWIRE_HOOK | 0.0006066 | 5.17e+04 | crafted |
| 196 | SHIELD | 0.0005937 | 5.28e+04 | crafted |
| 197 | TRAPPED_CHEST | 0.0005219 | 6e+04 | crafted |
| 198 | BLUE_CARPET | 0.0004655 | 6.73e+04 | crafted |
| 199 | BLACK_WOOL_SLAB | 0.0004439 | 7.06e+04 | crafted |
| 200 | BLACK_CUSHION | 0.0004439 | 7.06e+04 | crafted |
| 201 | BLACK_BED | 0.0004271 | 7.34e+04 | crafted |
| 202 | GLOW_LICHEN | 0.0004216 | 7.43e+04 | block |
| 203 | OAK_LOG | 0.0004034 | 7.77e+04 | drop block |
| 204 | SMITHING_TABLE | 0.0003801 | 8.24e+04 | crafted |
| 205 | BLUE_WOOL_SLAB | 0.0003752 | 8.35e+04 | crafted |
| 206 | BLUE_CUSHION | 0.0003752 | 8.35e+04 | crafted |
| 207 | HEAVY_WEIGHTED_PRESSURE_PLATE | 0.0003722 | 8.42e+04 | crafted |
| 208 | SHEARS | 0.0003722 | 8.42e+04 | crafted |
| 209 | IRON_CHAIN | 0.0003722 | 8.42e+04 | crafted |
| 210 | BLUE_BED | 0.0003665 | 8.55e+04 | crafted |
| 211 | IRON_HOE | 0.0003524 | 8.89e+04 | crafted |
| 212 | IRON_SWORD | 0.0003524 | 8.89e+04 | crafted |
| 213 | MUSHROOM_STEW | 0.0003195 | 9.81e+04 | crafted |
| 214 | BUSH | 0.0002881 | 1.09e+05 | block |
| 215 | STRIPPED_SPRUCE_LOG | 0.0002773 | 1.13e+05 | drop block |
| 216 | BLACK_WOOL_STAIRS | 0.000275 | 1.14e+05 | crafted |
| 217 | BLACK_BANNER | 0.0002685 | 1.17e+05 | crafted |
| 218 | BUCKET | 0.0002677 | 1.17e+05 | crafted |
| 219 | BLUE_WOOL_STAIRS | 0.0002676 | 1.17e+05 | crafted |
| 220 | IRON_AXE | 0.0002573 | 1.22e+05 | crafted |
| 221 | IRON_PICKAXE | 0.0002573 | 1.22e+05 | crafted |
| 222 | ORANGE_GLAZED_TERRACOTTA | 0.0002539 | 1.23e+05 | drop block |
| 223 | ORANGE_TERRACOTTA | 0.0002415 | 1.3e+05 | drop block |
| 224 | WHITE_HARNESS | 0.0002377 | 1.32e+05 | crafted |
| 225 | WHITE_WOOL | 0.0002332 | 1.34e+05 | drop |
| 226 | BUNDLE | 0.0002324 | 1.35e+05 | crafted |
| 227 | STRING | 0.0002319 | 1.35e+05 | drop |
| 228 | IRON_BOOTS | 0.0002161 | 1.45e+05 | crafted |
| 229 | IRON_TRAPDOOR | 0.0002161 | 1.45e+05 | crafted |
| 230 | LAPIS_BLOCK | 0.0002137 | 1.47e+05 | crafted |
| 231 | IRON_HELMET | 0.0001857 | 1.69e+05 | crafted |
| 232 | MINECART | 0.0001857 | 1.69e+05 | crafted |
| 233 | FURNACE_MINECART | 0.0001856 | 1.69e+05 | crafted |
| 234 | BLAST_FURNACE | 0.0001856 | 1.69e+05 | crafted |
| 235 | HOPPER | 0.0001765 | 1.78e+05 | crafted |
| 236 | CHEST_MINECART | 0.0001765 | 1.78e+05 | crafted |
| 237 | MUD_BRICK_SLAB | 0.0001742 | 1.8e+05 | drop block |
| 238 | IRON_BARS | 0.0001658 | 1.89e+05 | crafted |
| 239 | IRON_DOOR | 0.0001658 | 1.89e+05 | crafted |
| 240 | BLUE_BANNER | 0.0001645 | 1.9e+05 | crafted |
| 241 | PURPLE_HARNESS | 0.0001629 | 1.92e+05 | crafted |
| 242 | RAIL | 0.0001618 | 1.94e+05 | crafted |
| 243 | CAULDRON | 0.000152 | 2.06e+05 | crafted |
| 244 | IRON_LEGGINGS | 0.000152 | 2.06e+05 | crafted |
| 245 | INFESTED_STONE | 0.0001472 | 2.13e+05 | block |
| 246 | PURPLE_WOOL | 0.000147 | 2.13e+05 | crafted |
| 247 | SUGAR_CANE | 0.0001449 | 2.16e+05 | drop block |
| 248 | SUSPICIOUS_STEW | 0.0001445 | 2.17e+05 | crafted |
| 249 | YELLOW_HARNESS | 0.0001436 | 2.18e+05 | crafted |
| 250 | RED_HARNESS | 0.0001426 | 2.2e+05 | crafted |
| 251 | IRON_CHESTPLATE | 0.000142 | 2.21e+05 | crafted |
| 252 | PURPLE_CARPET | 0.0001406 | 2.23e+05 | crafted |
| 253 | PURPLE_WOOL_SLAB | 0.0001402 | 2.24e+05 | crafted |
| 254 | PURPLE_BED | 0.0001389 | 2.26e+05 | crafted |
| 255 | YELLOW_WOOL | 0.0001372 | 2.28e+05 | crafted |
| 256 | YELLOW_CARPET | 0.0001347 | 2.33e+05 | crafted |
| 257 | RAW_IRON_BLOCK | 0.0001344 | 2.33e+05 | crafted |
| 258 | IRON_BLOCK | 0.0001344 | 2.33e+05 | crafted |
| 259 | RED_CARPET | 0.0001333 | 2.35e+05 | crafted |
| 260 | RED_WOOL | 0.0001326 | 2.36e+05 | crafted |
| 261 | YELLOW_CUSHION | 0.0001297 | 2.42e+05 | crafted |
| 262 | YELLOW_WOOL_SLAB | 0.0001297 | 2.42e+05 | crafted |
| 263 | RED_CUSHION | 0.0001296 | 2.42e+05 | crafted |
| 264 | RED_WOOL_SLAB | 0.0001296 | 2.42e+05 | crafted |
| 265 | PURPLE_WOOL_STAIRS | 0.000129 | 2.43e+05 | crafted |
| 266 | YELLOW_BED | 0.0001283 | 2.44e+05 | crafted |
| 267 | RED_BED | 0.0001281 | 2.45e+05 | crafted |
| 268 | WHITE_CARPET | 0.0001271 | 2.46e+05 | crafted |
| 269 | HOPPER_MINECART | 0.0001245 | 2.52e+05 | crafted |
| 270 | FISHING_ROD | 0.0001239 | 2.53e+05 | crafted |
| 271 | LOOM | 0.0001239 | 2.53e+05 | crafted |
| 272 | RED_WOOL_STAIRS | 0.0001195 | 2.62e+05 | crafted |
| 273 | YELLOW_WOOL_STAIRS | 0.0001182 | 2.65e+05 | crafted |
| 274 | PURPLE_CUSHION | 0.0001176 | 2.66e+05 | crafted |
| 275 | STRIPPED_SPRUCE_WOOD | 0.0001139 | 2.75e+05 | drop block |
| 276 | YELLOW_TERRACOTTA | 0.0001129 | 2.78e+05 | crafted |
| 277 | YELLOW_GLAZED_TERRACOTTA | 0.0001129 | 2.78e+05 | crafted |
| 278 | YELLOW_CONCRETE_POWDER | 0.0001127 | 2.78e+05 | crafted |
| 279 | YELLOW_STAINED_GLASS_PANE | 0.0001121 | 2.79e+05 | crafted |
| 280 | YELLOW_STAINED_GLASS | 0.0001121 | 2.8e+05 | crafted |
| 281 | YELLOW_DYE | 0.000112 | 2.8e+05 | crafted |
| 282 | ANVIL | 0.000104 | 3.01e+05 | crafted |
| 283 | CROSSBOW | 9.768e-05 | 3.21e+05 | crafted |
| 284 | WHITE_WOOL_SLAB | 9.683e-05 | 3.24e+05 | crafted |
| 285 | WHITE_CUSHION | 9.683e-05 | 3.24e+05 | crafted |
| 286 | WHITE_BED | 9.555e-05 | 3.28e+05 | crafted |
| 287 | RED_TERRACOTTA | 9.348e-05 | 3.35e+05 | crafted |
| 288 | RED_GLAZED_TERRACOTTA | 9.348e-05 | 3.35e+05 | crafted |
| 289 | PURPLE_TERRACOTTA | 9.245e-05 | 3.39e+05 | crafted |
| 290 | PURPLE_GLAZED_TERRACOTTA | 9.245e-05 | 3.39e+05 | crafted |
| 291 | PURPLE_CONCRETE_POWDER | 9.164e-05 | 3.42e+05 | crafted |
| 292 | RED_CONCRETE_POWDER | 9.158e-05 | 3.42e+05 | crafted |
| 293 | PURPLE_STAINED_GLASS_PANE | 9.115e-05 | 3.44e+05 | crafted |
| 294 | PURPLE_STAINED_GLASS | 9.11e-05 | 3.44e+05 | crafted |
| 295 | PURPLE_DYE | 9.103e-05 | 3.44e+05 | crafted |
| 296 | RED_STAINED_GLASS_PANE | 9.087e-05 | 3.45e+05 | crafted |
| 297 | RED_STAINED_GLASS | 9.081e-05 | 3.45e+05 | crafted |
| 298 | RED_DYE | 9.067e-05 | 3.46e+05 | crafted |
| 299 | BOW | 8.94e-05 | 3.51e+05 | crafted |
| 300 | LAVA | 8.911e-05 | 3.52e+05 | block |
| 301 | ORANGE_WOOL_STAIRS | 7.674e-05 | 4.08e+05 | crafted |
| 302 | ORANGE_WOOL_SLAB | 7.667e-05 | 4.09e+05 | crafted |
| 303 | ORANGE_BED | 7.408e-05 | 4.23e+05 | crafted |
| 304 | ORANGE_CARPET | 7.372e-05 | 4.25e+05 | crafted |
| 305 | ORANGE_HARNESS | 7.259e-05 | 4.32e+05 | crafted |
| 306 | ORANGE_WOOL | 6.922e-05 | 4.53e+05 | crafted |
| 307 | WHITE_WOOL_STAIRS | 6.919e-05 | 4.53e+05 | crafted |
| 308 | ORANGE_CUSHION | 6.858e-05 | 4.57e+05 | crafted |
| 309 | WHITE_BANNER | 6.845e-05 | 4.58e+05 | crafted |
| 310 | LEAD | 6.253e-05 | 5.01e+05 | crafted |
| 311 | RED_MUSHROOM | 5.49e-05 | 5.71e+05 | drop block |
| 312 | ORANGE_CONCRETE_POWDER | 5.149e-05 | 6.09e+05 | crafted |
| 313 | ORANGE_STAINED_GLASS_PANE | 5.133e-05 | 6.11e+05 | crafted |
| 314 | ORANGE_STAINED_GLASS | 5.131e-05 | 6.11e+05 | crafted |
| 315 | ORANGE_DYE | 5.129e-05 | 6.11e+05 | crafted |
| 316 | CYAN_TERRACOTTA | 4.674e-05 | 6.7e+05 | drop block |
| 317 | SUGAR | 4.374e-05 | 7.16e+05 | crafted |
| 318 | EMERALD | 4.312e-05 | 7.27e+05 | drop |
| 319 | YELLOW_BANNER | 3.501e-05 | 8.95e+05 | crafted |
| 320 | RED_BANNER | 3.427e-05 | 9.14e+05 | crafted |
| 321 | PURPLE_BANNER | 3.418e-05 | 9.17e+05 | crafted |
| 322 | OAK_PLANKS | 3.332e-05 | 9.4e+05 | crafted |
| 323 | OAK_PRESSURE_PLATE | 3.332e-05 | 9.4e+05 | crafted |
| 324 | OAK_SLAB | 3.332e-05 | 9.4e+05 | crafted |
| 325 | OAK_BUTTON | 3.332e-05 | 9.4e+05 | crafted |
| 326 | OAK_FENCE | 3.315e-05 | 9.45e+05 | crafted |
| 327 | OAK_FENCE_GATE | 3.315e-05 | 9.45e+05 | crafted |
| 328 | WHITE_TERRACOTTA | 3.132e-05 | 1e+06 | drop block |
| 329 | EMERALD_ORE | 2.82e-05 | 1.11e+06 | block |
| 330 | FLETCHING_TABLE | 2.732e-05 | 1.15e+06 | drop block |
| 331 | CYAN_GLAZED_TERRACOTTA | 2.231e-05 | 1.4e+06 | crafted |
| 332 | OAK_STAIRS | 1.801e-05 | 1.74e+06 | crafted |
| 333 | OAK_TRAPDOOR | 1.801e-05 | 1.74e+06 | crafted |
| 334 | OAK_BOAT | 1.801e-05 | 1.74e+06 | crafted |
| 335 | OAK_DOOR | 1.801e-05 | 1.74e+06 | crafted |
| 336 | OAK_SIGN | 1.796e-05 | 1.74e+06 | crafted |
| 337 | OAK_CHEST_BOAT | 1.792e-05 | 1.75e+06 | crafted |
| 338 | PAPER | 1.7e-05 | 1.84e+06 | crafted |
| 339 | BOOK | 1.699e-05 | 1.84e+06 | crafted |
| 340 | FIELD_MASONED_BANNER_PATTERN | 1.699e-05 | 1.84e+06 | crafted |
| 341 | NAME_TAG | 1.694e-05 | 1.85e+06 | crafted |
| 342 | ORANGE_BANNER | 1.637e-05 | 1.91e+06 | crafted |
| 343 | CARTOGRAPHY_TABLE | 1.628e-05 | 1.92e+06 | crafted |
| 344 | WHITE_GLAZED_TERRACOTTA | 1.495e-05 | 2.1e+06 | crafted |
| 345 | SUSPICIOUS_GRAVEL | 1.058e-05 | 2.96e+06 | block |
| 346 | OAK_WOOD | 1.046e-05 | 3e+06 | crafted |
| 347 | EMERALD_BLOCK | 8.417e-06 | 3.72e+06 | crafted |
| 348 | BOOKSHELF | 8.19e-06 | 3.83e+06 | crafted |
| 349 | LECTERN | 8.022e-06 | 3.91e+06 | crafted |
| 350 | SPRUCE_SHELF | 3.021e-06 | 1.04e+07 | crafted |
| 351 | SPRUCE_HANGING_SIGN | 2.996e-06 | 1.05e+07 | crafted |
