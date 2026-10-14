# Block Shuffle item ranking (final settings, includes stand-on blocks, smelting time)

Pool = crafting-propagated drops (max-entropy model: obtainability priors, which count recipe yields for ingredients but need only one of the item itself, and make bulk gathering cheaper (each further unit of a raw material, counted through every craft the recipe needs, costs 0.85 of the one before), with exact per-primitive budgets; recipes that undo or sidestep another are ignored) PLUS the literal blocks a player can stand on, as in ConstantItemGenerator.getBagForPlayers. Occlusion cost scale 1, mob drop weight 140. The propagated bag and the stand-on bag each make up half of the final weight, game level 1, AIR drops filtered.

**New: smelting time.** A round is 300 s and one furnace load takes 10 s, with 4 furnaces running in parallel. An item whose recipe tree needs s rounds of smelting, s = max(longest chain of dependent smelts, ceil(total smelts / 4)), can only be gathered in the remaining 1 - 10s/300 of the round, so its target obtainability is multiplied by 1 - s/30 (0 once s reaches 30). Applied once to the whole tree and to the item itself only, not to the ingredients it feeds into other recipes. Weights are then refitted to the same per-primitive budgets, so what smelted items lose goes to the other things made from the same raw materials. "Smelt rounds" is s, averaged by weight over the ways of making the item (and over biomes), so it is fractional when part of an item comes from gathering and part from smelting; 0 means no smelting. All 4559 recipes of the Paper 26.3 server are loaded (the previous ranking used the same recipe set).

Source: "drop" = obtainable by breaking a block / killing a mob, "block" = can be stood on, "crafted" = only reachable through recipes.

## A. Pooled over 7 biome start points

Average of per-biome shares: taiga, forest, plains, dark_forest, birch_forest, savanna, desert.

| #   | Item                          | Chance %  | Times rarer than #1 | Smelt rounds | Source     |
|-----|-------------------------------|-----------|---------------------|--------------|------------|
| 1   | DIRT                          | 34.97     | 1                   | 0            | drop block |
| 2   | STONE                         | 10.29     | 3.4                 | 1            | block      |
| 3   | GRASS_BLOCK                   | 7.027     | 4.98                | 0            | block      |
| 4   | WATER                         | 5.635     | 6.21                | 0            | block      |
| 5   | OAK_LEAVES                    | 3.287     | 10.6                | 0            | block      |
| 6   | LEAF_LITTER                   | 3.138     | 11.1                | 0            | drop block |
| 7   | BIRCH_LEAVES                  | 2.467     | 14.2                | 0            | block      |
| 8   | SAND                          | 2.259     | 15.5                | 0            | drop block |
| 9   | SPRUCE_LEAVES                 | 1.923     | 18.2                | 0            | block      |
| 10  | SANDSTONE                     | 1.492     | 23.4                | 0            | drop block |
| 11  | COBBLESTONE                   | 1.257     | 27.8                | 0            | drop block |
| 12  | STONE_BUTTON                  | 1.111     | 31.5                | 1            | crafted    |
| 13  | SMOOTH_STONE_SLAB             | 1.075     | 32.5                | 2            | drop block |
| 14  | SMOOTH_STONE                  | 1.073     | 32.6                | 2            | crafted    |
| 15  | STONE_BRICK_SLAB              | 0.9783    | 35.7                | 1            | drop block |
| 16  | CHISELED_STONE_BRICKS         | 0.9767    | 35.8                | 1            | crafted    |
| 17  | STONE_BRICK_STAIRS            | 0.9546    | 36.6                | 1.1          | drop block |
| 18  | STONE_BRICK_WALL              | 0.9539    | 36.7                | 1.1          | crafted    |
| 19  | DARK_OAK_LEAVES               | 0.8853    | 39.5                | 0            | block      |
| 20  | GLASS                         | 0.6995    | 50                  | 1            | crafted    |
| 21  | SHORT_GRASS                   | 0.6382    | 54.8                | 0            | block      |
| 22  | SMOOTH_SANDSTONE              | 0.6257    | 55.9                | 1            | crafted    |
| 23  | STONE_PRESSURE_PLATE          | 0.6007    | 58.2                | 1            | crafted    |
| 24  | SNOW                          | 0.4947    | 70.7                | 0            | block      |
| 25  | COBBLESTONE_SLAB              | 0.4477    | 78.1                | 0            | drop block |
| 26  | STONE_SLAB                    | 0.432     | 80.9                | 1            | crafted    |
| 27  | GRAVEL                        | 0.4109    | 85.1                | 0            | drop block |
| 28  | ANDESITE                      | 0.4059    | 86.1                | 0            | drop block |
| 29  | PALE_MOSS_BLOCK               | 0.3534    | 98.9                | 0            | drop block |
| 30  | STONE_BRICKS                  | 0.3505    | 99.8                | 1            | drop block |
| 31  | CRACKED_STONE_BRICKS          | 0.3379    | 103                 | 2            | drop block |
| 32  | GRANITE                       | 0.313     | 112                 | 0            | drop block |
| 33  | DARK_OAK_LOG                  | 0.3012    | 116                 | 0            | drop block |
| 34  | DIORITE                       | 0.2936    | 119                 | 0            | drop block |
| 35  | COBBLESTONE_STAIRS            | 0.2917    | 120                 | 0            | drop block |
| 36  | COBBLESTONE_WALL              | 0.2899    | 121                 | 0            | drop block |
| 37  | CHISELED_SANDSTONE            | 0.2899    | 121                 | 0            | crafted    |
| 38  | SANDSTONE_SLAB                | 0.2899    | 121                 | 0            | crafted    |
| 39  | SMOOTH_SANDSTONE_SLAB         | 0.2802    | 125                 | 1            | crafted    |
| 40  | OAK_LOG                       | 0.2776    | 126                 | 0            | drop block |
| 41  | GLASS_BOTTLE                  | 0.2719    | 129                 | 1            | crafted    |
| 42  | FEATHER                       | 0.2632    | 133                 | 0            | drop       |
| 43  | BIRCH_LOG                     | 0.2629    | 133                 | 0            | drop block |
| 44  | SPRUCE_LOG                    | 0.2623    | 133                 | 0            | drop block |
| 45  | STONE_STAIRS                  | 0.2584    | 135                 | 2            | crafted    |
| 46  | CUT_SANDSTONE_SLAB            | 0.2491    | 140                 | 0            | crafted    |
| 47  | CUT_SANDSTONE                 | 0.2491    | 140                 | 0            | crafted    |
| 48  | FURNACE                       | 0.237     | 148                 | 0            | crafted    |
| 49  | SANDSTONE_WALL                | 0.212     | 165                 | 0            | crafted    |
| 50  | SANDSTONE_STAIRS              | 0.212     | 165                 | 0            | crafted    |
| 51  | BIRCH_SAPLING                 | 0.2111    | 166                 | 0            | drop       |
| 52  | SMOOTH_SANDSTONE_STAIRS       | 0.1978    | 177                 | 2            | crafted    |
| 53  | STICK                         | 0.1962    | 178                 | 0            | drop       |
| 54  | COARSE_DIRT                   | 0.1928    | 181                 | 0            | drop block |
| 55  | ACACIA_LEAVES                 | 0.1909    | 183                 | 0            | block      |
| 56  | CHICKEN                       | 0.1759    | 199                 | 0            | drop       |
| 57  | WILDFLOWERS                   | 0.1731    | 202                 | 0            | drop block |
| 58  | COOKED_CHICKEN                | 0.17      | 206                 | 1            | crafted    |
| 59  | GLASS_PANE                    | 0.1696    | 206                 | 2            | block      |
| 60  | PALE_OAK_LEAVES               | 0.1628    | 215                 | 0            | block      |
| 61  | ICE                           | 0.1614    | 217                 | 0            | block      |
| 62  | DARK_OAK_PLANKS               | 0.1583    | 221                 | 0            | drop block |
| 63  | COAL_ORE                      | 0.1284    | 272                 | 0            | block      |
| 64  | BIRCH_PLANKS                  | 0.1237    | 283                 | 0            | drop block |
| 65  | PALE_MOSS_CARPET              | 0.123     | 284                 | 0            | drop block |
| 66  | COAL                          | 0.1184    | 295                 | 0            | drop       |
| 67  | PODZOL                        | 0.108     | 324                 | 0            | block      |
| 68  | BEEF                          | 0.1025    | 341                 | 0            | drop       |
| 69  | PORKCHOP                      | 0.1014    | 345                 | 0            | drop       |
| 70  | COOKED_BEEF                   | 0.09909   | 353                 | 1            | crafted    |
| 71  | COOKED_PORKCHOP               | 0.09799   | 357                 | 1            | crafted    |
| 72  | PALE_OAK_LOG                  | 0.09012   | 388                 | 0            | drop block |
| 73  | LEVER                         | 0.08523   | 410                 | 0            | drop block |
| 74  | STONE_SWORD                   | 0.07704   | 454                 | 0            | crafted    |
| 75  | BROWN_WOOL                    | 0.07226   | 484                 | 0            | drop block |
| 76  | YELLOW_DYE                    | 0.07099   | 493                 | 0            | crafted    |
| 77  | ANDESITE_SLAB                 | 0.07091   | 493                 | 0            | crafted    |
| 78  | STONE_SHOVEL                  | 0.06971   | 502                 | 0            | crafted    |
| 79  | STONE_SPEAR                   | 0.06971   | 502                 | 0            | crafted    |
| 80  | STONE_HOE                     | 0.06496   | 538                 | 0            | crafted    |
| 81  | LEATHER                       | 0.06146   | 569                 | 0            | drop       |
| 82  | POLISHED_ANDESITE             | 0.06144   | 569                 | 0            | drop block |
| 83  | POLISHED_ANDESITE_SLAB        | 0.0614    | 569                 | 0            | crafted    |
| 84  | STONE_PICKAXE                 | 0.06131   | 570                 | 0            | crafted    |
| 85  | STONE_AXE                     | 0.06131   | 570                 | 0            | crafted    |
| 86  | TALL_GRASS                    | 0.05414   | 646                 | 0            | block      |
| 87  | BOWL                          | 0.05385   | 649                 | 0            | crafted    |
| 88  | CRAFTING_TABLE                | 0.05179   | 675                 | 0            | crafted    |
| 89  | CHARCOAL                      | 0.05085   | 688                 | 1            | crafted    |
| 90  | TORCH                         | 0.04951   | 706                 | 0.35         | drop block |
| 91  | WHITE_WOOL                    | 0.04922   | 710                 | 0            | drop block |
| 92  | WHITE_CARPET                  | 0.04784   | 731                 | 0            | drop block |
| 93  | WOODEN_SHOVEL                 | 0.04749   | 736                 | 0            | crafted    |
| 94  | WOODEN_SPEAR                  | 0.04749   | 736                 | 0            | crafted    |
| 95  | ANDESITE_STAIRS               | 0.04605   | 759                 | 0            | crafted    |
| 96  | ANDESITE_WALL                 | 0.04605   | 759                 | 0            | crafted    |
| 97  | GRANITE_SLAB                  | 0.04445   | 787                 | 0            | crafted    |
| 98  | WOODEN_SWORD                  | 0.04187   | 835                 | 0            | crafted    |
| 99  | FERN                          | 0.04092   | 854                 | 0            | block      |
| 100 | WOODEN_HOE                    | 0.04019   | 870                 | 0            | crafted    |
| 101 | LADDER                        | 0.03977   | 879                 | 0            | drop block |
| 102 | PALE_HANGING_MOSS             | 0.03974   | 880                 | 0            | block      |
| 103 | COPPER_ORE                    | 0.03916   | 893                 | 0            | block      |
| 104 | BROWN_CARPET                  | 0.03915   | 893                 | 0            | drop block |
| 105 | POLISHED_ANDESITE_STAIRS      | 0.03867   | 904                 | 0            | crafted    |
| 106 | WHEAT_SEEDS                   | 0.03843   | 910                 | 0            | drop       |
| 107 | RABBIT                        | 0.03786   | 924                 | 0            | drop       |
| 108 | WOODEN_AXE                    | 0.03777   | 926                 | 0            | crafted    |
| 109 | WOODEN_PICKAXE                | 0.03777   | 926                 | 0            | crafted    |
| 110 | COOKED_RABBIT                 | 0.0366    | 955                 | 1            | crafted    |
| 111 | POLISHED_GRANITE_SLAB         | 0.03588   | 974                 | 0            | crafted    |
| 112 | POLISHED_GRANITE              | 0.03588   | 974                 | 0            | crafted    |
| 113 | NETHERRACK                    | 0.03541   | 988                 | 0            | drop block |
| 114 | GRINDSTONE                    | 0.03518   | 994                 | 1            | crafted    |
| 115 | ARMOR_STAND                   | 0.03509   | 996                 | 2            | crafted    |
| 116 | LARGE_FERN                    | 0.03318   | 1.05e+03            | 0            | block      |
| 117 | RED_CARPET                    | 0.03302   | 1.06e+03            | 0            | drop block |
| 118 | CHEST                         | 0.03043   | 1.15e+03            | 0            | drop block |
| 119 | DARK_OAK_BUTTON               | 0.03013   | 1.16e+03            | 0            | crafted    |
| 120 | BROWN_CUSHION                 | 0.02802   | 1.25e+03            | 0            | crafted    |
| 121 | BROWN_WOOL_SLAB               | 0.02802   | 1.25e+03            | 0            | crafted    |
| 122 | GRANITE_WALL                  | 0.02754   | 1.27e+03            | 0            | crafted    |
| 123 | GRANITE_STAIRS                | 0.02754   | 1.27e+03            | 0            | crafted    |
| 124 | PAINTING                      | 0.02747   | 1.27e+03            | 0            | crafted    |
| 125 | BROWN_MUSHROOM_BLOCK          | 0.02621   | 1.33e+03            | 0            | block      |
| 126 | OAK_PLANKS                    | 0.02458   | 1.42e+03            | 0            | drop block |
| 127 | RABBIT_HIDE                   | 0.02456   | 1.42e+03            | 0            | drop       |
| 128 | MOSSY_COBBLESTONE             | 0.02438   | 1.43e+03            | 0            | drop block |
| 129 | POLISHED_GRANITE_STAIRS       | 0.02358   | 1.48e+03            | 0            | crafted    |
| 130 | COAL_BLOCK                    | 0.02312   | 1.51e+03            | 0            | crafted    |
| 131 | BIRCH_BUTTON                  | 0.02226   | 1.57e+03            | 0            | crafted    |
| 132 | DIORITE_SLAB                  | 0.02202   | 1.59e+03            | 0            | crafted    |
| 133 | ACACIA_LOG                    | 0.02197   | 1.59e+03            | 0            | drop block |
| 134 | DARK_OAK_FENCE                | 0.02135   | 1.64e+03            | 0            | drop block |
| 135 | DARK_OAK_PRESSURE_PLATE       | 0.02088   | 1.67e+03            | 0            | crafted    |
| 136 | LEATHER_BOOTS                 | 0.02082   | 1.68e+03            | 0            | crafted    |
| 137 | COMPOSTER                     | 0.02005   | 1.74e+03            | 0            | crafted    |
| 138 | OAK_SLAB                      | 0.01985   | 1.76e+03            | 0            | drop block |
| 139 | OAK_STAIRS                    | 0.01963   | 1.78e+03            | 0            | drop block |
| 140 | WHITE_WOOL_SLAB               | 0.01893   | 1.85e+03            | 0            | crafted    |
| 141 | WHITE_CUSHION                 | 0.01893   | 1.85e+03            | 0            | crafted    |
| 142 | LEATHER_HELMET                | 0.0183    | 1.91e+03            | 0            | crafted    |
| 143 | DARK_OAK_SLAB                 | 0.01818   | 1.92e+03            | 0            | crafted    |
| 144 | BROWN_MUSHROOM                | 0.01787   | 1.96e+03            | 0            | drop block |
| 145 | POLISHED_DIORITE              | 0.01777   | 1.97e+03            | 0            | crafted    |
| 146 | POLISHED_DIORITE_SLAB         | 0.01777   | 1.97e+03            | 0            | crafted    |
| 147 | BROWN_WOOL_STAIRS             | 0.01736   | 2.01e+03            | 0            | crafted    |
| 148 | CAMPFIRE                      | 0.01735   | 2.02e+03            | 0.37         | crafted    |
| 149 | BARREL                        | 0.01619   | 2.16e+03            | 0            | crafted    |
| 150 | CHISELED_BOOKSHELF            | 0.01615   | 2.17e+03            | 0            | crafted    |
| 151 | LEATHER_LEGGINGS              | 0.01557   | 2.25e+03            | 0            | crafted    |
| 152 | LEATHER_HORSE_ARMOR           | 0.01557   | 2.25e+03            | 0            | crafted    |
| 153 | SMOKER                        | 0.01547   | 2.26e+03            | 0            | crafted    |
| 154 | BIRCH_PRESSURE_PLATE          | 0.01541   | 2.27e+03            | 0            | crafted    |
| 155 | OAK_BUTTON                    | 0.01525   | 2.29e+03            | 0            | crafted    |
| 156 | OAK_PRESSURE_PLATE            | 0.01498   | 2.33e+03            | 0            | crafted    |
| 157 | LEATHER_CHESTPLATE            | 0.01477   | 2.37e+03            | 0            | crafted    |
| 158 | RED_MUSHROOM_BLOCK            | 0.01452   | 2.41e+03            | 0            | block      |
| 159 | OAK_FENCE                     | 0.01438   | 2.43e+03            | 0            | drop block |
| 160 | BIRCH_SLAB                    | 0.01423   | 2.46e+03            | 0            | drop block |
| 161 | IRON_ORE                      | 0.0141    | 2.48e+03            | 0            | block      |
| 162 | TALL_SEAGRASS                 | 0.01379   | 2.54e+03            | 0            | block      |
| 163 | DIORITE_WALL                  | 0.01364   | 2.56e+03            | 0            | crafted    |
| 164 | DIORITE_STAIRS                | 0.01364   | 2.56e+03            | 0            | crafted    |
| 165 | BLACK_WOOL                    | 0.01306   | 2.68e+03            | 0            | drop block |
| 166 | KELP                          | 0.01272   | 2.75e+03            | 0            | drop block |
| 167 | DARK_OAK_STAIRS               | 0.01253   | 2.79e+03            | 0            | drop block |
| 168 | SEAGRASS                      | 0.01216   | 2.88e+03            | 0            | block      |
| 169 | SPRUCE_PRESSURE_PLATE         | 0.01186   | 2.95e+03            | 0            | crafted    |
| 170 | SPRUCE_SLAB                   | 0.01186   | 2.95e+03            | 0            | crafted    |
| 171 | SPRUCE_PLANKS                 | 0.01186   | 2.95e+03            | 0            | crafted    |
| 172 | SPRUCE_BUTTON                 | 0.01186   | 2.95e+03            | 0            | crafted    |
| 173 | WHITE_WOOL_STAIRS             | 0.01174   | 2.98e+03            | 0            | crafted    |
| 174 | CLAY                          | 0.01169   | 2.99e+03            | 0            | block      |
| 175 | POLISHED_DIORITE_STAIRS       | 0.01168   | 2.99e+03            | 0            | crafted    |
| 176 | KELP_PLANT                    | 0.01158   | 3.02e+03            | 0            | block      |
| 177 | DARK_OAK_FENCE_GATE           | 0.01147   | 3.05e+03            | 0            | drop block |
| 178 | DARK_OAK_BOAT                 | 0.01087   | 3.22e+03            | 0            | crafted    |
| 179 | BIRCH_FENCE_GATE              | 0.01063   | 3.29e+03            | 0            | crafted    |
| 180 | DARK_OAK_TRAPDOOR             | 0.01036   | 3.37e+03            | 0            | crafted    |
| 181 | DARK_OAK_DOOR                 | 0.01036   | 3.37e+03            | 0            | crafted    |
| 182 | YELLOW_WOOL                   | 0.01026   | 3.41e+03            | 0            | drop block |
| 183 | VINE                          | 0.009858  | 3.55e+03            | 0            | block      |
| 184 | BIRCH_FENCE                   | 0.009789  | 3.57e+03            | 0            | drop block |
| 185 | SPRUCE_FENCE                  | 0.009102  | 3.84e+03            | 0            | crafted    |
| 186 | NETHER_BRICK                  | 0.008997  | 3.89e+03            | 1            | crafted    |
| 187 | GRAY_WOOL                     | 0.00899   | 3.89e+03            | 0            | drop block |
| 188 | DRIED_KELP                    | 0.008973  | 3.9e+03             | 1            | crafted    |
| 189 | BIRCH_STAIRS                  | 0.008714  | 4.01e+03            | 0            | drop block |
| 190 | BUSH                          | 0.008179  | 4.28e+03            | 0            | block      |
| 191 | BIRCH_BOAT                    | 0.008088  | 4.32e+03            | 0            | crafted    |
| 192 | OAK_BOAT                      | 0.008085  | 4.33e+03            | 0            | crafted    |
| 193 | OAK_DOOR                      | 0.008072  | 4.33e+03            | 0            | crafted    |
| 194 | OAK_TRAPDOOR                  | 0.008072  | 4.33e+03            | 0            | crafted    |
| 195 | OAK_FENCE_GATE                | 0.008059  | 4.34e+03            | 0            | crafted    |
| 196 | SPRUCE_FENCE_GATE             | 0.007954  | 4.4e+03             | 0            | crafted    |
| 197 | BIRCH_DOOR                    | 0.0078    | 4.48e+03            | 0            | crafted    |
| 198 | BIRCH_TRAPDOOR                | 0.0078    | 4.48e+03            | 0            | crafted    |
| 199 | DANDELION                     | 0.007391  | 4.73e+03            | 0            | drop block |
| 200 | DARK_OAK_SIGN                 | 0.007297  | 4.79e+03            | 0            | crafted    |
| 201 | LIGHT_GRAY_WOOL               | 0.007097  | 4.93e+03            | 0            | drop block |
| 202 | BLACK_CARPET                  | 0.007009  | 4.99e+03            | 0            | crafted    |
| 203 | ITEM_FRAME                    | 0.006966  | 5.02e+03            | 0            | crafted    |
| 204 | PALE_OAK_SLAB                 | 0.006928  | 5.05e+03            | 0            | crafted    |
| 205 | PALE_OAK_PLANKS               | 0.006928  | 5.05e+03            | 0            | crafted    |
| 206 | PALE_OAK_PRESSURE_PLATE       | 0.006928  | 5.05e+03            | 0            | crafted    |
| 207 | PALE_OAK_BUTTON               | 0.006928  | 5.05e+03            | 0            | crafted    |
| 208 | PINK_TULIP                    | 0.006803  | 5.14e+03            | 0            | drop block |
| 209 | WHITE_BANNER                  | 0.006763  | 5.17e+03            | 0            | crafted    |
| 210 | LILAC                         | 0.006522  | 5.36e+03            | 0            | block      |
| 211 | SPRUCE_DOOR                   | 0.006413  | 5.45e+03            | 0            | crafted    |
| 212 | SPRUCE_STAIRS                 | 0.006413  | 5.45e+03            | 0            | crafted    |
| 213 | SPRUCE_TRAPDOOR               | 0.006413  | 5.45e+03            | 0            | crafted    |
| 214 | SPRUCE_BOAT                   | 0.006413  | 5.45e+03            | 0            | crafted    |
| 215 | SPRUCE_SIGN                   | 0.006388  | 5.47e+03            | 0            | crafted    |
| 216 | BIRCH_SIGN                    | 0.006282  | 5.57e+03            | 0            | crafted    |
| 217 | DRIPSTONE_BLOCK               | 0.006159  | 5.68e+03            | 0            | drop block |
| 218 | YELLOW_CARPET                 | 0.006117  | 5.72e+03            | 0            | crafted    |
| 219 | GRAY_CARPET                   | 0.005828  | 6e+03               | 0            | drop block |
| 220 | DARK_OAK_CHEST_BOAT           | 0.005827  | 6e+03               | 0            | crafted    |
| 221 | BIRCH_CHEST_BOAT              | 0.005785  | 6.04e+03            | 0            | crafted    |
| 222 | POPPY                         | 0.005745  | 6.09e+03            | 0            | drop block |
| 223 | JUNGLE_LEAVES                 | 0.005677  | 6.16e+03            | 0            | block      |
| 224 | PALE_OAK_FENCE                | 0.005603  | 6.24e+03            | 0            | crafted    |
| 225 | PALE_OAK_FENCE_GATE           | 0.005603  | 6.24e+03            | 0            | crafted    |
| 226 | WHITE_BED                     | 0.005586  | 6.26e+03            | 0            | crafted    |
| 227 | OAK_SIGN                      | 0.005383  | 6.5e+03             | 0            | crafted    |
| 228 | RAW_COPPER                    | 0.00511   | 6.84e+03            | 0            | drop       |
| 229 | BLACK_CUSHION                 | 0.00504   | 6.94e+03            | 0            | crafted    |
| 230 | BLACK_WOOL_SLAB               | 0.00504   | 6.94e+03            | 0            | crafted    |
| 231 | COPPER_TORCH                  | 0.004995  | 7e+03               | 1            | crafted    |
| 232 | COPPER_INGOT                  | 0.00494   | 7.08e+03            | 1            | crafted    |
| 233 | COPPER_NUGGET                 | 0.00494   | 7.08e+03            | 1            | crafted    |
| 234 | YELLOW_WOOL_SLAB              | 0.004783  | 7.31e+03            | 0            | crafted    |
| 235 | YELLOW_CUSHION                | 0.004783  | 7.31e+03            | 0            | crafted    |
| 236 | BROWN_HARNESS                 | 0.004699  | 7.44e+03            | 1            | crafted    |
| 237 | OAK_WOOD                      | 0.004644  | 7.53e+03            | 0            | crafted    |
| 238 | WHITE_HARNESS                 | 0.00451   | 7.75e+03            | 1            | crafted    |
| 239 | OAK_CHEST_BOAT                | 0.004498  | 7.77e+03            | 0            | crafted    |
| 240 | MUSHROOM_STEW                 | 0.004476  | 7.81e+03            | 0            | crafted    |
| 241 | MUSHROOM_STEM                 | 0.004467  | 7.83e+03            | 0            | block      |
| 242 | SUGAR_CANE                    | 0.004361  | 8.02e+03            | 0            | drop block |
| 243 | MOSSY_COBBLESTONE_SLAB        | 0.004142  | 8.44e+03            | 0            | crafted    |
| 244 | LIGHT_GRAY_CARPET             | 0.004042  | 8.65e+03            | 0            | drop block |
| 245 | DARK_OAK_WOOD                 | 0.003814  | 9.17e+03            | 0            | crafted    |
| 246 | SPRUCE_CHEST_BOAT             | 0.003806  | 9.19e+03            | 0            | crafted    |
| 247 | PALE_OAK_DOOR                 | 0.003745  | 9.34e+03            | 0            | crafted    |
| 248 | PALE_OAK_BOAT                 | 0.003745  | 9.34e+03            | 0            | crafted    |
| 249 | PALE_OAK_STAIRS               | 0.003745  | 9.34e+03            | 0            | crafted    |
| 250 | PALE_OAK_TRAPDOOR             | 0.003745  | 9.34e+03            | 0            | crafted    |
| 251 | BIRCH_WOOD                    | 0.003737  | 9.36e+03            | 0            | crafted    |
| 252 | SPRUCE_WOOD                   | 0.003723  | 9.39e+03            | 0            | crafted    |
| 253 | MAGMA_BLOCK                   | 0.003659  | 9.56e+03            | 0            | drop block |
| 254 | BRUSH                         | 0.003534  | 9.89e+03            | 1            | crafted    |
| 255 | YELLOW_WOOL_STAIRS            | 0.003483  | 1e+04               | 0            | crafted    |
| 256 | GRAY_CUSHION                  | 0.003458  | 1.01e+04            | 0            | crafted    |
| 257 | GRAY_WOOL_SLAB                | 0.003458  | 1.01e+04            | 0            | crafted    |
| 258 | COPPER_SHOVEL                 | 0.003366  | 1.04e+04            | 1            | crafted    |
| 259 | COPPER_SPEAR                  | 0.003366  | 1.04e+04            | 1            | crafted    |
| 260 | TOTEM_OF_UNDYING              | 0.003351  | 1.04e+04            | 0            | drop       |
| 261 | PALE_OAK_SIGN                 | 0.003312  | 1.06e+04            | 0            | crafted    |
| 262 | CLAY_BALL                     | 0.003293  | 1.06e+04            | 0            | drop       |
| 263 | BRICK                         | 0.003184  | 1.1e+04             | 1            | crafted    |
| 264 | BLACK_WOOL_STAIRS             | 0.003123  | 1.12e+04            | 0            | crafted    |
| 265 | OXEYE_DAISY                   | 0.00311   | 1.12e+04            | 0            | drop block |
| 266 | ROSE_BUSH                     | 0.003076  | 1.14e+04            | 0            | block      |
| 267 | TALL_DRY_GRASS                | 0.002963  | 1.18e+04            | 0            | block      |
| 268 | JUNGLE_LOG                    | 0.002943  | 1.19e+04            | 0            | drop block |
| 269 | PALE_OAK_CHEST_BOAT           | 0.002935  | 1.19e+04            | 0            | crafted    |
| 270 | YELLOW_BED                    | 0.002933  | 1.19e+04            | 0            | crafted    |
| 271 | LIGHT_GRAY_WOOL_SLAB          | 0.00286   | 1.22e+04            | 0            | crafted    |
| 272 | LIGHT_GRAY_CUSHION            | 0.002858  | 1.22e+04            | 0            | crafted    |
| 273 | NETHER_BRICK_WALL             | 0.002824  | 1.24e+04            | 1            | crafted    |
| 274 | NETHER_BRICK_STAIRS           | 0.002824  | 1.24e+04            | 1            | crafted    |
| 275 | CHISELED_NETHER_BRICKS        | 0.002824  | 1.24e+04            | 1            | crafted    |
| 276 | NETHER_BRICK_SLAB             | 0.002824  | 1.24e+04            | 1            | crafted    |
| 277 | NETHER_BRICKS                 | 0.002824  | 1.24e+04            | 1            | crafted    |
| 278 | CRACKED_NETHER_BRICKS         | 0.002726  | 1.28e+04            | 2            | crafted    |
| 279 | DEAD_BUSH                     | 0.002674  | 1.31e+04            | 0            | block      |
| 280 | COPPER_CHAIN                  | 0.00267   | 1.31e+04            | 1            | crafted    |
| 281 | POINTED_DRIPSTONE             | 0.002656  | 1.32e+04            | 0            | drop block |
| 282 | CACTUS                        | 0.002591  | 1.35e+04            | 0            | drop block |
| 283 | MOSSY_COBBLESTONE_WALL        | 0.002566  | 1.36e+04            | 0            | crafted    |
| 284 | MOSSY_COBBLESTONE_STAIRS      | 0.002566  | 1.36e+04            | 0            | crafted    |
| 285 | COPPER_LANTERN                | 0.002541  | 1.38e+04            | 1            | crafted    |
| 286 | GRAY_HARNESS                  | 0.002541  | 1.38e+04            | 1            | crafted    |
| 287 | BLACK_BANNER                  | 0.002498  | 1.4e+04             | 0            | crafted    |
| 288 | SHORT_DRY_GRASS               | 0.002454  | 1.42e+04            | 0            | block      |
| 289 | RED_TULIP                     | 0.002351  | 1.49e+04            | 0            | drop block |
| 290 | YELLOW_BANNER                 | 0.002348  | 1.49e+04            | 0            | crafted    |
| 291 | LIGHT_GRAY_WOOL_STAIRS        | 0.002321  | 1.51e+04            | 0            | crafted    |
| 292 | PALE_OAK_WOOD                 | 0.002174  | 1.61e+04            | 0            | crafted    |
| 293 | COPPER_SWORD                  | 0.002172  | 1.61e+04            | 1            | crafted    |
| 294 | GRAY_WOOL_STAIRS              | 0.002161  | 1.62e+04            | 0            | crafted    |
| 295 | COPPER_HOE                    | 0.002093  | 1.67e+04            | 1            | crafted    |
| 296 | BLACK_BED                     | 0.002005  | 1.74e+04            | 0            | crafted    |
| 297 | DEAD_BRAIN_CORAL_BLOCK        | 0.001973  | 1.77e+04            | 0            | drop       |
| 298 | PEONY                         | 0.001963  | 1.78e+04            | 0            | drop block |
| 299 | WHITE_TULIP                   | 0.001953  | 1.79e+04            | 0            | drop block |
| 300 | LIGHT_GRAY_BED                | 0.00194   | 1.8e+04             | 0            | crafted    |
| 301 | LIGHTNING_ROD                 | 0.00192   | 1.82e+04            | 1            | crafted    |
| 302 | AZURE_BLUET                   | 0.001914  | 1.83e+04            | 0            | drop block |
| 303 | GRAY_BED                      | 0.001886  | 1.85e+04            | 0            | crafted    |
| 304 | FLOWER_POT                    | 0.001867  | 1.87e+04            | 0.8          | drop       |
| 305 | GRAY_BANNER                   | 0.001857  | 1.88e+04            | 0            | drop       |
| 306 | MOSS_BLOCK                    | 0.00184   | 1.9e+04             | 0            | drop block |
| 307 | BOOKSHELF                     | 0.001747  | 2e+04               | 0            | block      |
| 308 | DEAD_TUBE_CORAL_BLOCK         | 0.001715  | 2.04e+04            | 0            | drop       |
| 309 | SUGAR                         | 0.001708  | 2.05e+04            | 0            | crafted    |
| 310 | CLOSED_EYEBLOSSOM             | 0.001677  | 2.08e+04            | 0            | drop block |
| 311 | DRIED_KELP_BLOCK              | 0.001631  | 2.14e+04            | 3            | crafted    |
| 312 | ACACIA_STAIRS                 | 0.001603  | 2.18e+04            | 0            | drop block |
| 313 | COPPER_PICKAXE                | 0.001594  | 2.19e+04            | 1            | crafted    |
| 314 | COPPER_AXE                    | 0.001594  | 2.19e+04            | 1            | crafted    |
| 315 | PINK_CARPET                   | 0.001586  | 2.2e+04             | 0            | drop block |
| 316 | COPPER_TRAPDOOR               | 0.00155   | 2.26e+04            | 1            | crafted    |
| 317 | COPPER_BOOTS                  | 0.00155   | 2.26e+04            | 1            | crafted    |
| 318 | LAPIS_ORE                     | 0.001535  | 2.28e+04            | 0            | block      |
| 319 | DEAD_BUBBLE_CORAL_BLOCK       | 0.001518  | 2.3e+04             | 0            | drop       |
| 320 | PINK_WOOL_SLAB                | 0.001513  | 2.31e+04            | 0            | crafted    |
| 321 | ORANGE_TULIP                  | 0.001508  | 2.32e+04            | 0            | drop block |
| 322 | PINK_CUSHION                  | 0.001503  | 2.33e+04            | 0            | crafted    |
| 323 | PINK_WOOL_STAIRS              | 0.001489  | 2.35e+04            | 0            | crafted    |
| 324 | BOOK                          | 0.001488  | 2.35e+04            | 0            | drop       |
| 325 | FARMLAND                      | 0.001461  | 2.39e+04            | 0            | block      |
| 326 | BLACK_HARNESS                 | 0.001416  | 2.47e+04            | 1            | crafted    |
| 327 | PINK_WOOL                     | 0.001394  | 2.51e+04            | 0            | crafted    |
| 328 | BROWN_BANNER                  | 0.001392  | 2.51e+04            | 0            | crafted    |
| 329 | PUMPKIN                       | 0.00132   | 2.65e+04            | 0            | drop block |
| 330 | LANTERN                       | 0.001293  | 2.7e+04             | 1            | crafted    |
| 331 | COPPER_HELMET                 | 0.001286  | 2.72e+04            | 2            | crafted    |
| 332 | SADDLE                        | 0.001258  | 2.78e+04            | 1            | crafted    |
| 333 | RAW_IRON                      | 0.00125   | 2.8e+04             | 0            | drop       |
| 334 | NETHER_BRICK_FENCE            | 0.001229  | 2.84e+04            | 5            | crafted    |
| 335 | RED_WOOL_STAIRS               | 0.001215  | 2.88e+04            | 0            | crafted    |
| 336 | IRON_NUGGET                   | 0.001208  | 2.89e+04            | 1            | crafted    |
| 337 | IRON_INGOT                    | 0.001208  | 2.89e+04            | 1            | crafted    |
| 338 | STONECUTTER                   | 0.001202  | 2.91e+04            | 1            | crafted    |
| 339 | LILY_OF_THE_VALLEY            | 0.001201  | 2.91e+04            | 0            | drop block |
| 340 | GLOW_LICHEN                   | 0.001169  | 2.99e+04            | 0            | block      |
| 341 | COPPER_DOOR                   | 0.001149  | 3.04e+04            | 2            | crafted    |
| 342 | COPPER_BARS                   | 0.001149  | 3.04e+04            | 2            | crafted    |
| 343 | RED_WOOL_SLAB                 | 0.001144  | 3.06e+04            | 0            | crafted    |
| 344 | RED_CUSHION                   | 0.001144  | 3.06e+04            | 0            | crafted    |
| 345 | LIGHT_GRAY_BANNER             | 0.001122  | 3.12e+04            | 0            | crafted    |
| 346 | ACACIA_PLANKS                 | 0.001116  | 3.13e+04            | 0            | drop block |
| 347 | DEAD_HORN_CORAL_BLOCK         | 0.001113  | 3.14e+04            | 0            | drop       |
| 348 | PINK_HARNESS                  | 0.001113  | 3.14e+04            | 1            | crafted    |
| 349 | LODESTONE                     | 0.001108  | 3.15e+04            | 3.9          | crafted    |
| 350 | OBSIDIAN                      | 0.001108  | 3.16e+04            | 0            | drop block |
| 351 | BROWN_BED                     | 0.001104  | 3.17e+04            | 0            | crafted    |
| 352 | IRON_SHOVEL                   | 0.001087  | 3.22e+04            | 1            | crafted    |
| 353 | IRON_SPEAR                    | 0.001087  | 3.22e+04            | 1            | crafted    |
| 354 | DEAD_FIRE_CORAL_BLOCK         | 0.001074  | 3.25e+04            | 0            | drop       |
| 355 | MOSS_CARPET                   | 0.001073  | 3.26e+04            | 0            | drop block |
| 356 | COPPER_LEGGINGS               | 0.001053  | 3.32e+04            | 2            | crafted    |
| 357 | RED_WOOL                      | 0.001046  | 3.34e+04            | 0            | drop block |
| 358 | BRICK_SLAB                    | 0.000999  | 3.5e+04             | 1            | crafted    |
| 359 | TERRACOTTA                    | 0.000999  | 3.5e+04             | 1            | crafted    |
| 360 | BRICKS                        | 0.000999  | 3.5e+04             | 1            | crafted    |
| 361 | BRICK_STAIRS                  | 0.000999  | 3.5e+04             | 1            | crafted    |
| 362 | BRICK_WALL                    | 0.000999  | 3.5e+04             | 1            | crafted    |
| 363 | DECORATED_POT                 | 0.000999  | 3.5e+04             | 1            | crafted    |
| 364 | RAW_COPPER_BLOCK              | 0.0009976 | 3.51e+04            | 0            | crafted    |
| 365 | PINK_BED                      | 0.0009837 | 3.55e+04            | 0            | crafted    |
| 366 | COPPER_CHESTPLATE             | 0.0009834 | 3.56e+04            | 2            | crafted    |
| 367 | LIGHT_GRAY_HARNESS            | 0.0009524 | 3.67e+04            | 1            | crafted    |
| 368 | DARK_OAK_SAPLING              | 0.0009509 | 3.68e+04            | 0            | drop block |
| 369 | PINK_DYE                      | 0.0009127 | 3.83e+04            | 0            | crafted    |
| 370 | COPPER_BLOCK                  | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 371 | COPPER_GRATE                  | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 372 | CUT_COPPER                    | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 373 | CUT_COPPER_STAIRS             | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 374 | CHISELED_COPPER               | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 375 | CUT_COPPER_SLAB               | 0.0008979 | 3.89e+04            | 3            | crafted    |
| 376 | BRAIN_CORAL_BLOCK             | 0.0008935 | 3.91e+04            | 0            | block      |
| 377 | PINK_CONCRETE_POWDER          | 0.0008821 | 3.96e+04            | 0            | crafted    |
| 378 | TRIPWIRE_HOOK                 | 0.0008446 | 4.14e+04            | 1            | crafted    |
| 379 | SHIELD                        | 0.0008445 | 4.14e+04            | 1            | crafted    |
| 380 | ALLIUM                        | 0.0008322 | 4.2e+04             | 0            | drop block |
| 381 | WHEAT                         | 0.0008213 | 4.26e+04            | 0            | block      |
| 382 | MOSSY_STONE_BRICK_STAIRS      | 0.0008103 | 4.32e+04            | 0.5          | drop block |
| 383 | GREEN_CONCRETE_POWDER         | 0.0008099 | 4.32e+04            | 1            | crafted    |
| 384 | GREEN_DYE                     | 0.0007997 | 4.37e+04            | 1            | crafted    |
| 385 | TUBE_CORAL_BLOCK              | 0.0007765 | 4.5e+04             | 0            | block      |
| 386 | GREEN_STAINED_GLASS_PANE      | 0.0007687 | 4.55e+04            | 2            | crafted    |
| 387 | LIGHT_GRAY_DYE                | 0.0007634 | 4.58e+04            | 0            | crafted    |
| 388 | FIREFLY_BUSH                  | 0.0007602 | 4.6e+04             | 0            | drop block |
| 389 | ORANGE_CARPET                 | 0.0007557 | 4.63e+04            | 0            | crafted    |
| 390 | RED_BED                       | 0.000752  | 4.65e+04            | 0            | crafted    |
| 391 | COPPER_CHEST                  | 0.000748  | 4.67e+04            | 2            | crafted    |
| 392 | PINK_STAINED_GLASS_PANE       | 0.0007425 | 4.71e+04            | 2            | crafted    |
| 393 | GREEN_STAINED_GLASS           | 0.0007402 | 4.72e+04            | 3            | crafted    |
| 394 | ORANGE_WOOL_STAIRS            | 0.0007348 | 4.76e+04            | 0            | crafted    |
| 395 | TRAPPED_CHEST                 | 0.0007286 | 4.8e+04             | 1            | crafted    |
| 396 | PINK_STAINED_GLASS            | 0.0007143 | 4.9e+04             | 2            | crafted    |
| 397 | LIGHT_GRAY_CONCRETE_POWDER    | 0.0007123 | 4.91e+04            | 0            | crafted    |
| 398 | RED_HARNESS                   | 0.0007017 | 4.98e+04            | 1            | crafted    |
| 399 | RED_DYE                       | 0.0006887 | 5.08e+04            | 0            | crafted    |
| 400 | BUBBLE_CORAL_BLOCK            | 0.0006874 | 5.09e+04            | 0            | block      |
| 401 | PAPER                         | 0.000664  | 5.27e+04            | 0            | crafted    |
| 402 | ORANGE_WOOL_SLAB              | 0.0006581 | 5.31e+04            | 0            | crafted    |
| 403 | HEAVY_WEIGHTED_PRESSURE_PLATE | 0.000653  | 5.35e+04            | 1            | crafted    |
| 404 | IRON_CHAIN                    | 0.000653  | 5.35e+04            | 1            | crafted    |
| 405 | SHEARS                        | 0.000653  | 5.35e+04            | 1            | crafted    |
| 406 | ACACIA_BUTTON                 | 0.000643  | 5.44e+04            | 0            | crafted    |
| 407 | IRON_SWORD                    | 0.0006268 | 5.58e+04            | 1            | crafted    |
| 408 | ACACIA_PRESSURE_PLATE         | 0.0006263 | 5.58e+04            | 0            | crafted    |
| 409 | ACACIA_SLAB                   | 0.0006208 | 5.63e+04            | 0            | crafted    |
| 410 | LECTERN                       | 0.0006197 | 5.64e+04            | 0            | crafted    |
| 411 | IRON_HOE                      | 0.0006149 | 5.69e+04            | 1            | crafted    |
| 412 | GREEN_CARPET                  | 0.0006097 | 5.73e+04            | 0            | drop block |
| 413 | YELLOW_HARNESS                | 0.0006069 | 5.76e+04            | 1            | crafted    |
| 414 | LIGHT_GRAY_STAINED_GLASS_PANE | 0.0006012 | 5.82e+04            | 2            | crafted    |
| 415 | ORANGE_CUSHION                | 0.0005899 | 5.93e+04            | 0            | crafted    |
| 416 | CACTUS_FLOWER                 | 0.0005799 | 6.03e+04            | 0            | drop block |
| 417 | LIGHT_GRAY_STAINED_GLASS      | 0.0005766 | 6.06e+04            | 2            | crafted    |
| 418 | RED_MUSHROOM                  | 0.0005755 | 6.08e+04            | 0            | drop block |
| 419 | MOSSY_STONE_BRICKS            | 0.0005697 | 6.14e+04            | 0.5          | drop block |
| 420 | MOSSY_STONE_BRICK_SLAB        | 0.000568  | 6.16e+04            | 0.5          | drop block |
| 421 | YELLOW_CONCRETE_POWDER        | 0.0005625 | 6.22e+04            | 0            | crafted    |
| 422 | PUMPKIN_SEEDS                 | 0.0005256 | 6.65e+04            | 0            | crafted    |
| 423 | RED_CONCRETE_POWDER           | 0.0005188 | 6.74e+04            | 0            | crafted    |
| 424 | HORN_CORAL_BLOCK              | 0.0005042 | 6.94e+04            | 0            | block      |
| 425 | ORANGE_WOOL                   | 0.0004991 | 7.01e+04            | 0            | crafted    |
| 426 | FIRE_CORAL_BLOCK              | 0.0004865 | 7.19e+04            | 0            | block      |
| 427 | BUBBLE_CORAL_WALL_FAN         | 0.0004794 | 7.29e+04            | 0            | block      |
| 428 | DIRT_PATH                     | 0.0004783 | 7.31e+04            | 0            | block      |
| 429 | ORANGE_BED                    | 0.0004761 | 7.34e+04            | 0            | crafted    |
| 430 | BUCKET                        | 0.0004696 | 7.45e+04            | 1            | crafted    |
| 431 | YELLOW_STAINED_GLASS_PANE     | 0.0004692 | 7.45e+04            | 2            | crafted    |
| 432 | NAME_TAG                      | 0.000455  | 7.68e+04            | 1            | crafted    |
| 433 | YELLOW_STAINED_GLASS          | 0.0004544 | 7.69e+04            | 2            | crafted    |
| 434 | IRON_PICKAXE                  | 0.0004494 | 7.78e+04            | 1            | crafted    |
| 435 | IRON_AXE                      | 0.0004494 | 7.78e+04            | 1            | crafted    |
| 436 | RED_STAINED_GLASS_PANE        | 0.000445  | 7.86e+04            | 2            | crafted    |
| 437 | SMITHING_TABLE                | 0.0004441 | 7.87e+04            | 1            | crafted    |
| 438 | MOSSY_STONE_BRICK_WALL        | 0.0004292 | 8.15e+04            | 0.5          | crafted    |
| 439 | RED_STAINED_GLASS             | 0.0004284 | 8.16e+04            | 2            | crafted    |
| 440 | FIRE_CORAL_WALL_FAN           | 0.0004112 | 8.5e+04             | 0            | block      |
| 441 | HORN_CORAL_WALL_FAN           | 0.0004046 | 8.64e+04            | 0            | block      |
| 442 | BLUE_CARPET                   | 0.0004027 | 8.68e+04            | 0            | drop block |
| 443 | ORANGE_DYE                    | 0.0003969 | 8.81e+04            | 0            | crafted    |
| 444 | BRAIN_CORAL_WALL_FAN          | 0.0003793 | 9.22e+04            | 0            | block      |
| 445 | IRON_BOOTS                    | 0.0003791 | 9.22e+04            | 1            | crafted    |
| 446 | IRON_TRAPDOOR                 | 0.0003791 | 9.22e+04            | 1            | crafted    |
| 447 | IRON_DOOR                     | 0.0003702 | 9.45e+04            | 2            | drop block |
| 448 | BEE_NEST                      | 0.0003667 | 9.54e+04            | 0            | block      |
| 449 | SUSPICIOUS_STEW               | 0.0003526 | 9.92e+04            | 0            | crafted    |
| 450 | TUBE_CORAL_WALL_FAN           | 0.0003429 | 1.02e+05            | 0            | block      |
| 451 | ACACIA_BOAT                   | 0.0003377 | 1.04e+05            | 0            | crafted    |
| 452 | ACACIA_FENCE_GATE             | 0.0003373 | 1.04e+05            | 0            | crafted    |
| 453 | ACACIA_TRAPDOOR               | 0.0003367 | 1.04e+05            | 0            | crafted    |
| 454 | ACACIA_DOOR                   | 0.0003367 | 1.04e+05            | 0            | crafted    |
| 455 | ACACIA_FENCE                  | 0.0003351 | 1.04e+05            | 0            | crafted    |
| 456 | JUNGLE_PRESSURE_PLATE         | 0.0003195 | 1.09e+05            | 0            | crafted    |
| 457 | JUNGLE_SLAB                   | 0.0003195 | 1.09e+05            | 0            | crafted    |
| 458 | JUNGLE_BUTTON                 | 0.0003195 | 1.09e+05            | 0            | crafted    |
| 459 | JUNGLE_PLANKS                 | 0.0003195 | 1.09e+05            | 0            | crafted    |
| 460 | CAULDRON                      | 0.0003168 | 1.1e+05             | 2            | drop block |
| 461 | MINECART                      | 0.0003145 | 1.11e+05            | 2            | crafted    |
| 462 | IRON_HELMET                   | 0.0003145 | 1.11e+05            | 2            | crafted    |
| 463 | FURNACE_MINECART              | 0.0003137 | 1.11e+05            | 2            | crafted    |
| 464 | IRON_BARS                     | 0.0003106 | 1.13e+05            | 2            | drop block |
| 465 | GRAY_DYE                      | 0.0003033 | 1.15e+05            | 0            | crafted    |
| 466 | BLAST_FURNACE                 | 0.0003024 | 1.16e+05            | 3            | crafted    |
| 467 | FIELD_MASONED_BANNER_PATTERN  | 0.0002998 | 1.17e+05            | 1            | crafted    |
| 468 | SEA_PICKLE                    | 0.0002957 | 1.18e+05            | 0            | drop block |
| 469 | LILY_PAD                      | 0.0002793 | 1.25e+05            | 0            | drop block |
| 470 | RAIL                          | 0.0002755 | 1.27e+05            | 2            | crafted    |
| 471 | JUNGLE_FENCE                  | 0.0002723 | 1.28e+05            | 0            | crafted    |
| 472 | AZALEA                        | 0.0002699 | 1.3e+05             | 0            | drop block |
| 473 | PINK_BANNER                   | 0.0002681 | 1.3e+05             | 0            | crafted    |
| 474 | HOPPER                        | 0.0002676 | 1.31e+05            | 2            | crafted    |
| 475 | CHEST_MINECART                | 0.0002676 | 1.31e+05            | 2            | crafted    |
| 476 | ORANGE_HARNESS                | 0.0002575 | 1.36e+05            | 1            | crafted    |
| 477 | IRON_LEGGINGS                 | 0.0002575 | 1.36e+05            | 2            | crafted    |
| 478 | LAVA                          | 0.0002527 | 1.38e+05            | 0            | block      |
| 479 | CORNFLOWER                    | 0.0002512 | 1.39e+05            | 0            | drop block |
| 480 | RAW_IRON_BLOCK                | 0.000244  | 1.43e+05            | 0            | crafted    |
| 481 | ACACIA_SIGN                   | 0.0002412 | 1.45e+05            | 0            | crafted    |
| 482 | IRON_CHESTPLATE               | 0.0002405 | 1.45e+05            | 2            | crafted    |
| 483 | CRYING_OBSIDIAN               | 0.0002397 | 1.46e+05            | 0            | drop block |
| 484 | JUNGLE_FENCE_GATE             | 0.0002387 | 1.46e+05            | 0            | crafted    |
| 485 | CARTOGRAPHY_TABLE             | 0.0002229 | 1.57e+05            | 0            | crafted    |
| 486 | IRON_BLOCK                    | 0.0002196 | 1.59e+05            | 3            | crafted    |
| 487 | ORANGE_CONCRETE_POWDER        | 0.0002184 | 1.6e+05             | 0            | crafted    |
| 488 | RED_BANNER                    | 0.0002111 | 1.66e+05            | 0            | crafted    |
| 489 | ORANGE_STAINED_GLASS_PANE     | 0.0001948 | 1.79e+05            | 2            | crafted    |
| 490 | ACACIA_CHEST_BOAT             | 0.0001946 | 1.8e+05             | 0            | crafted    |
| 491 | BLUE_CONCRETE_POWDER          | 0.0001921 | 1.82e+05            | 0            | crafted    |
| 492 | ORANGE_STAINED_GLASS          | 0.0001905 | 1.84e+05            | 2            | crafted    |
| 493 | ACACIA_WOOD                   | 0.0001905 | 1.84e+05            | 0            | crafted    |
| 494 | MELON_STEM                    | 0.0001876 | 1.86e+05            | 0            | block      |
| 495 | HOPPER_MINECART               | 0.0001868 | 1.87e+05            | 3            | crafted    |
| 496 | BLUE_DYE                      | 0.0001825 | 1.92e+05            | 0            | crafted    |
| 497 | LAPIS_LAZULI                  | 0.0001819 | 1.92e+05            | 0            | drop       |
| 498 | JUNGLE_STAIRS                 | 0.0001727 | 2.02e+05            | 0            | crafted    |
| 499 | JUNGLE_BOAT                   | 0.0001727 | 2.02e+05            | 0            | crafted    |
| 500 | JUNGLE_DOOR                   | 0.0001727 | 2.02e+05            | 0            | crafted    |
| 501 | JUNGLE_TRAPDOOR               | 0.0001727 | 2.02e+05            | 0            | crafted    |
| 502 | LIGHT_BLUE_CARPET             | 0.0001724 | 2.03e+05            | 0            | drop block |
| 503 | BLUE_STAINED_GLASS_PANE       | 0.0001721 | 2.03e+05            | 2            | crafted    |
| 504 | BLUE_STAINED_GLASS            | 0.000171  | 2.04e+05            | 2            | crafted    |
| 505 | YELLOW_TERRACOTTA             | 0.0001691 | 2.07e+05            | 2            | crafted    |
| 506 | JUNGLE_SIGN                   | 0.0001664 | 2.1e+05             | 0            | crafted    |
| 507 | YELLOW_GLAZED_TERRACOTTA      | 0.0001631 | 2.14e+05            | 3            | crafted    |
| 508 | DEEPSLATE                     | 0.0001542 | 2.27e+05            | 1            | block      |
| 509 | COCOA                         | 0.0001504 | 2.32e+05            | 0            | block      |
| 510 | PINK_TERRACOTTA               | 0.0001499 | 2.33e+05            | 2            | crafted    |
| 511 | GREEN_GLAZED_TERRACOTTA       | 0.0001493 | 2.34e+05            | 3            | crafted    |
| 512 | GREEN_TERRACOTTA              | 0.0001493 | 2.34e+05            | 3            | crafted    |
| 513 | PINK_GLAZED_TERRACOTTA        | 0.0001445 | 2.42e+05            | 3            | crafted    |
| 514 | RED_TERRACOTTA                | 0.0001401 | 2.5e+05             | 2            | crafted    |
| 515 | BLUE_TERRACOTTA               | 0.0001398 | 2.5e+05             | 2            | crafted    |
| 516 | BLUE_WOOL_STAIRS              | 0.0001396 | 2.5e+05             | 0            | crafted    |
| 517 | ANVIL                         | 0.0001384 | 2.53e+05            | 8            | crafted    |
| 518 | FLOWERING_AZALEA              | 0.0001368 | 2.56e+05            | 0            | drop block |
| 519 | RED_GLAZED_TERRACOTTA         | 0.0001351 | 2.59e+05            | 3            | crafted    |
| 520 | BLUE_GLAZED_TERRACOTTA        | 0.0001348 | 2.59e+05            | 3            | crafted    |
| 521 | LIGHT_GRAY_TERRACOTTA         | 0.0001334 | 2.62e+05            | 2            | crafted    |
| 522 | LIGHT_GRAY_GLAZED_TERRACOTTA  | 0.0001286 | 2.72e+05            | 3            | crafted    |
| 523 | MAGENTA_WOOL_STAIRS           | 0.000123  | 2.84e+05            | 0            | crafted    |
| 524 | POTTED_RED_TULIP              | 0.0001219 | 2.87e+05            | 0            | block      |
| 525 | MELON_SEEDS                   | 0.0001216 | 2.88e+05            | 0            | drop       |
| 526 | HORN_CORAL_FAN                | 0.0001197 | 2.92e+05            | 0            | block      |
| 527 | BAMBOO                        | 0.000111  | 3.15e+05            | 0            | drop block |
| 528 | MAGENTA_WOOL_SLAB             | 0.0001085 | 3.22e+05            | 0            | crafted    |
| 529 | MAGENTA_CUSHION               | 0.0001084 | 3.23e+05            | 0            | crafted    |
| 530 | POTTED_OXEYE_DAISY            | 0.0001082 | 3.23e+05            | 0            | block      |
| 531 | ORANGE_BANNER                 | 0.0001081 | 3.23e+05            | 0            | crafted    |
| 532 | PURPLE_WOOL_STAIRS            | 0.0001074 | 3.26e+05            | 0            | crafted    |
| 533 | ORANGE_TERRACOTTA             | 0.0001071 | 3.27e+05            | 2            | crafted    |
| 534 | BLUE_CUSHION                  | 0.0001043 | 3.35e+05            | 0            | crafted    |
| 535 | BLUE_WOOL_SLAB                | 0.0001043 | 3.35e+05            | 0            | crafted    |
| 536 | ORANGE_GLAZED_TERRACOTTA      | 0.0001033 | 3.39e+05            | 3            | crafted    |
| 537 | MAGENTA_BED                   | 0.0001011 | 3.46e+05            | 0            | crafted    |
| 538 | WALL_TORCH                    | 0.000101  | 3.46e+05            | 0            | block      |
| 539 | MAGENTA_CARPET                | 0.0001005 | 3.48e+05            | 0            | crafted    |
| 540 | JUNGLE_WOOD                   | 0.0001003 | 3.49e+05            | 0            | crafted    |
| 541 | JUNGLE_CHEST_BOAT             | 9.396e-05 | 3.72e+05            | 0            | crafted    |
| 542 | BLUE_BED                      | 9.207e-05 | 3.8e+05             | 0            | crafted    |
| 543 | PURPLE_WOOL_SLAB              | 9.053e-05 | 3.86e+05            | 0            | crafted    |
| 544 | MAGENTA_WOOL                  | 8.84e-05  | 3.96e+05            | 0            | crafted    |
| 545 | POTTED_WHITE_TULIP            | 8.75e-05  | 4e+05               | 0            | block      |
| 546 | MAGENTA_HARNESS               | 8.604e-05 | 4.06e+05            | 1            | crafted    |
| 547 | PURPLE_CARPET                 | 8.301e-05 | 4.21e+05            | 0            | crafted    |
| 548 | LIME_CONCRETE_POWDER          | 8.143e-05 | 4.29e+05            | 1            | crafted    |
| 549 | LIME_DYE                      | 8.058e-05 | 4.34e+05            | 1            | crafted    |
| 550 | CYAN_CONCRETE_POWDER          | 8.045e-05 | 4.35e+05            | 1            | crafted    |
| 551 | FIRE_CORAL                    | 7.946e-05 | 4.4e+05             | 0            | block      |
| 552 | CYAN_DYE                      | 7.798e-05 | 4.48e+05            | 1            | crafted    |
| 553 | LIME_STAINED_GLASS_PANE       | 7.777e-05 | 4.5e+05             | 2            | crafted    |
| 554 | PURPLE_BED                    | 7.528e-05 | 4.65e+05            | 0            | crafted    |
| 555 | CYAN_STAINED_GLASS_PANE       | 7.526e-05 | 4.65e+05            | 2            | crafted    |
| 556 | LIME_STAINED_GLASS            | 7.498e-05 | 4.66e+05            | 3            | crafted    |
| 557 | PURPLE_HARNESS                | 7.442e-05 | 4.7e+05             | 1            | crafted    |
| 558 | MAGENTA_CONCRETE_POWDER       | 7.349e-05 | 4.76e+05            | 0            | crafted    |
| 559 | BRAIN_CORAL                   | 7.344e-05 | 4.76e+05            | 0            | block      |
| 560 | PURPLE_WOOL                   | 7.259e-05 | 4.82e+05            | 0            | crafted    |
| 561 | CYAN_STAINED_GLASS            | 7.257e-05 | 4.82e+05            | 3            | crafted    |
| 562 | BUBBLE_CORAL_FAN              | 7.113e-05 | 4.92e+05            | 0            | block      |
| 563 | BIG_DRIPLEAF                  | 7.084e-05 | 4.94e+05            | 0            | drop block |
| 564 | MAGENTA_DYE                   | 7.068e-05 | 4.95e+05            | 0            | crafted    |
| 565 | CREAKING_HEART                | 7.039e-05 | 4.97e+05            | 0            | block      |
| 566 | BLUE_HARNESS                  | 6.764e-05 | 5.17e+05            | 1            | crafted    |
| 567 | MAGENTA_STAINED_GLASS_PANE    | 6.723e-05 | 5.2e+05             | 2            | crafted    |
| 568 | MAGENTA_STAINED_GLASS         | 6.604e-05 | 5.29e+05            | 2            | crafted    |
| 569 | WHITE_DYE                     | 6.577e-05 | 5.32e+05            | 0            | crafted    |
| 570 | PURPLE_CONCRETE_POWDER        | 6.494e-05 | 5.38e+05            | 0            | crafted    |
| 571 | POTTED_DANDELION              | 6.312e-05 | 5.54e+05            | 0            | block      |
| 572 | POTTED_POPPY                  | 6.271e-05 | 5.58e+05            | 0            | block      |
| 573 | PURPLE_DYE                    | 6.249e-05 | 5.6e+05             | 0            | crafted    |
| 574 | BLUE_WOOL                     | 6.077e-05 | 5.75e+05            | 0            | crafted    |
| 575 | PURPLE_CUSHION                | 5.917e-05 | 5.91e+05            | 0            | crafted    |
| 576 | PURPLE_STAINED_GLASS_PANE     | 5.906e-05 | 5.92e+05            | 2            | crafted    |
| 577 | TUBE_CORAL_FAN                | 5.896e-05 | 5.93e+05            | 0            | block      |
| 578 | PURPLE_STAINED_GLASS          | 5.855e-05 | 5.97e+05            | 2            | crafted    |
| 579 | PURPLE_TERRACOTTA             | 5.769e-05 | 6.06e+05            | 2            | crafted    |
| 580 | BROWN_CONCRETE_POWDER         | 5.637e-05 | 6.2e+05             | 0            | crafted    |
| 581 | BUBBLE_CORAL                  | 5.615e-05 | 6.23e+05            | 0            | block      |
| 582 | BROWN_DYE                     | 5.587e-05 | 6.26e+05            | 0            | crafted    |
| 583 | COCOA_BEANS                   | 5.587e-05 | 6.26e+05            | 0            | drop       |
| 584 | PURPLE_GLAZED_TERRACOTTA      | 5.563e-05 | 6.29e+05            | 3            | crafted    |
| 585 | CYAN_TERRACOTTA               | 5.395e-05 | 6.48e+05            | 3            | crafted    |
| 586 | CYAN_GLAZED_TERRACOTTA        | 5.395e-05 | 6.48e+05            | 3            | crafted    |
| 587 | LIME_GLAZED_TERRACOTTA        | 5.363e-05 | 6.52e+05            | 3            | crafted    |
| 588 | LIME_TERRACOTTA               | 5.363e-05 | 6.52e+05            | 3            | crafted    |
| 589 | BROWN_STAINED_GLASS_PANE      | 5.213e-05 | 6.71e+05            | 2            | crafted    |
| 590 | BROWN_STAINED_GLASS           | 5.213e-05 | 6.71e+05            | 2            | crafted    |
| 591 | TUBE_CORAL                    | 5.195e-05 | 6.73e+05            | 0            | block      |
| 592 | POTTED_AZURE_BLUET            | 5.195e-05 | 6.73e+05            | 0            | block      |
| 593 | BRAIN_CORAL_FAN               | 5.01e-05  | 6.98e+05            | 0            | block      |
| 594 | HORN_CORAL                    | 5.002e-05 | 6.99e+05            | 0            | block      |
| 595 | GREEN_WOOL                    | 4.963e-05 | 7.05e+05            | 0            | drop block |
| 596 | FIRE_CORAL_FAN                | 4.343e-05 | 8.05e+05            | 0            | block      |
| 597 | LAPIS_BLOCK                   | 4.311e-05 | 8.11e+05            | 0            | drop block |
| 598 | BROWN_TERRACOTTA              | 4.122e-05 | 8.48e+05            | 2            | crafted    |
| 599 | BROWN_GLAZED_TERRACOTTA       | 3.975e-05 | 8.8e+05             | 3            | crafted    |
| 600 | SWEET_BERRIES                 | 3.867e-05 | 9.04e+05            | 0            | drop       |
| 601 | MAGENTA_TERRACOTTA            | 3.675e-05 | 9.52e+05            | 2            | crafted    |
| 602 | MAGENTA_GLAZED_TERRACOTTA     | 3.543e-05 | 9.87e+05            | 3            | crafted    |
| 603 | FLOWER_BANNER_PATTERN         | 3.38e-05  | 1.03e+06            | 0            | crafted    |
| 604 | REDSTONE_ORE                  | 3.326e-05 | 1.05e+06            | 0            | block      |
| 605 | SWEET_BERRY_BUSH              | 2.931e-05 | 1.19e+06            | 0            | block      |
| 606 | RESIN_CLUMP                   | 2.169e-05 | 1.61e+06            | 0            | drop       |
| 607 | RESIN_BRICK                   | 2.096e-05 | 1.67e+06            | 1            | crafted    |
| 608 | MAGENTA_BANNER                | 2.087e-05 | 1.68e+06            | 0            | crafted    |
| 609 | LIGHT_BLUE_WOOL_STAIRS        | 2.065e-05 | 1.69e+06            | 0            | crafted    |
| 610 | LIGHT_BLUE_WOOL_SLAB          | 1.955e-05 | 1.79e+06            | 0            | crafted    |
| 611 | LIGHT_BLUE_BED                | 1.912e-05 | 1.83e+06            | 0            | crafted    |
| 612 | LIGHT_BLUE_WOOL               | 1.828e-05 | 1.91e+06            | 0            | crafted    |
| 613 | CAVE_VINES_PLANT              | 1.813e-05 | 1.93e+06            | 0            | block      |
| 614 | WHITE_TERRACOTTA              | 1.807e-05 | 1.94e+06            | 2            | crafted    |
| 615 | LIGHT_BLUE_HARNESS            | 1.758e-05 | 1.99e+06            | 1            | crafted    |
| 616 | WHITE_GLAZED_TERRACOTTA       | 1.742e-05 | 2.01e+06            | 3            | crafted    |
| 617 | WHITE_CONCRETE_POWDER         | 1.736e-05 | 2.01e+06            | 0            | crafted    |
| 618 | DEEPSLATE_TILE_STAIRS         | 1.709e-05 | 2.05e+06            | 0            | crafted    |
| 619 | DEEPSLATE_TILE_WALL           | 1.709e-05 | 2.05e+06            | 0            | crafted    |
| 620 | COBBLED_DEEPSLATE             | 1.709e-05 | 2.05e+06            | 0            | drop       |
| 621 | DEEPSLATE_TILE_SLAB           | 1.709e-05 | 2.05e+06            | 0            | crafted    |
| 622 | WHITE_STAINED_GLASS_PANE      | 1.601e-05 | 2.18e+06            | 2            | crafted    |
| 623 | WHITE_STAINED_GLASS           | 1.586e-05 | 2.2e+06             | 2            | crafted    |
| 624 | BIG_DRIPLEAF_STEM             | 1.497e-05 | 2.34e+06            | 0            | block      |
| 625 | BLUE_BANNER                   | 1.423e-05 | 2.46e+06            | 0            | crafted    |
| 626 | CAVE_VINES                    | 1.388e-05 | 2.52e+06            | 0            | block      |
| 627 | PURPLE_BANNER                 | 1.362e-05 | 2.57e+06            | 0            | crafted    |
| 628 | GRAY_WALL_BANNER              | 1.216e-05 | 2.88e+06            | 0            | block      |
| 629 | LIGHT_BLUE_CUSHION            | 1.186e-05 | 2.95e+06            | 0            | crafted    |
| 630 | LIGHT_BLUE_TERRACOTTA         | 1.043e-05 | 3.35e+06            | 2            | crafted    |
| 631 | LIGHT_BLUE_GLAZED_TERRACOTTA  | 1.006e-05 | 3.48e+06            | 3            | crafted    |
| 632 | LIGHT_BLUE_CONCRETE_POWDER    | 9.726e-06 | 3.6e+06             | 0            | crafted    |
| 633 | LIGHT_BLUE_DYE                | 9.428e-06 | 3.71e+06            | 0            | crafted    |
| 634 | REDSTONE                      | 9.094e-06 | 3.85e+06            | 0            | drop       |
| 635 | DROPPER                       | 9.09e-06  | 3.85e+06            | 0            | crafted    |
| 636 | REDSTONE_TORCH                | 9.056e-06 | 3.86e+06            | 0            | crafted    |
| 637 | LIGHT_BLUE_STAINED_GLASS_PANE | 9.011e-06 | 3.88e+06            | 2            | crafted    |
| 638 | LIGHT_BLUE_STAINED_GLASS      | 8.96e-06  | 3.9e+06             | 2            | crafted    |
| 639 | COMPASS                       | 8.263e-06 | 4.23e+06            | 1            | crafted    |
| 640 | MAP                           | 8.233e-06 | 4.25e+06            | 1            | crafted    |
| 641 | DIAMOND_ORE                   | 7.835e-06 | 4.46e+06            | 0            | block      |
| 642 | DETECTOR_RAIL                 | 7.834e-06 | 4.46e+06            | 2            | crafted    |
| 643 | ACTIVATOR_RAIL                | 7.756e-06 | 4.51e+06            | 2            | crafted    |
| 644 | CHISELED_DEEPSLATE            | 6.645e-06 | 5.26e+06            | 0            | crafted    |
| 645 | COBBLED_DEEPSLATE_SLAB        | 6.645e-06 | 5.26e+06            | 0            | crafted    |
| 646 | RESIN_BRICKS                  | 6.578e-06 | 5.32e+06            | 1            | crafted    |
| 647 | CHISELED_RESIN_BRICKS         | 6.578e-06 | 5.32e+06            | 1            | crafted    |
| 648 | RESIN_BRICK_SLAB              | 6.578e-06 | 5.32e+06            | 1            | crafted    |
| 649 | RESIN_BRICK_STAIRS            | 6.578e-06 | 5.32e+06            | 1            | crafted    |
| 650 | RESIN_BRICK_WALL              | 6.578e-06 | 5.32e+06            | 1            | crafted    |
| 651 | PISTON                        | 6.304e-06 | 5.55e+06            | 1            | crafted    |
| 652 | SPAWNER                       | 5.799e-06 | 6.03e+06            | 0            | block      |
| 653 | DEEPSLATE_TILES               | 5.365e-06 | 6.52e+06            | 0            | crafted    |
| 654 | DEEPSLATE_BRICKS              | 5.365e-06 | 6.52e+06            | 0            | crafted    |
| 655 | DEEPSLATE_BRICK_SLAB          | 5.365e-06 | 6.52e+06            | 0            | crafted    |
| 656 | POLISHED_DEEPSLATE_SLAB       | 5.365e-06 | 6.52e+06            | 0            | crafted    |
| 657 | POLISHED_DEEPSLATE            | 5.365e-06 | 6.52e+06            | 0            | crafted    |
| 658 | CRACKED_DEEPSLATE_BRICKS      | 5.186e-06 | 6.74e+06            | 1            | crafted    |
| 659 | CRACKED_DEEPSLATE_TILES       | 5.186e-06 | 6.74e+06            | 1            | crafted    |
| 660 | NOTE_BLOCK                    | 4.339e-06 | 8.06e+06            | 0            | crafted    |
| 661 | RESIN_BLOCK                   | 4.233e-06 | 8.26e+06            | 0            | crafted    |
| 662 | COBBLED_DEEPSLATE_WALL        | 4.117e-06 | 8.49e+06            | 0            | crafted    |
| 663 | COBBLED_DEEPSLATE_STAIRS      | 4.117e-06 | 8.49e+06            | 0            | crafted    |
| 664 | LIGHT_BLUE_BANNER             | 4.036e-06 | 8.66e+06            | 0            | crafted    |
| 665 | BAMBOO_BLOCK                  | 4.019e-06 | 8.7e+06             | 0            | crafted    |
| 666 | BAMBOO_PLANKS                 | 4.019e-06 | 8.7e+06             | 0            | crafted    |
| 667 | BAMBOO_BUTTON                 | 4.019e-06 | 8.7e+06             | 0            | crafted    |
| 668 | BAMBOO_PRESSURE_PLATE         | 4.019e-06 | 8.7e+06             | 0            | crafted    |
| 669 | BAMBOO_FENCE_GATE             | 4.01e-06  | 8.72e+06            | 0            | crafted    |
| 670 | POLISHED_DEEPSLATE_WALL       | 3.525e-06 | 9.92e+06            | 0            | crafted    |
| 671 | DEEPSLATE_BRICK_STAIRS        | 3.525e-06 | 9.92e+06            | 0            | crafted    |
| 672 | DEEPSLATE_BRICK_WALL          | 3.525e-06 | 9.92e+06            | 0            | crafted    |
| 673 | POLISHED_DEEPSLATE_STAIRS     | 3.525e-06 | 9.92e+06            | 0            | crafted    |
| 674 | REPEATER                      | 3.406e-06 | 1.03e+07            | 1            | crafted    |
| 675 | BAMBOO_MOSAIC                 | 3.263e-06 | 1.07e+07            | 0            | crafted    |
| 676 | BAMBOO_SLAB                   | 3.263e-06 | 1.07e+07            | 0            | crafted    |
| 677 | BAMBOO_FENCE                  | 3.258e-06 | 1.07e+07            | 0            | crafted    |
| 678 | CRAFTER                       | 3.177e-06 | 1.1e+07             | 2            | crafted    |
| 679 | BAMBOO_RAFT                   | 3.127e-06 | 1.12e+07            | 0            | crafted    |
| 680 | BAMBOO_STAIRS                 | 3.127e-06 | 1.12e+07            | 0            | crafted    |
| 681 | BAMBOO_DOOR                   | 3.127e-06 | 1.12e+07            | 0            | crafted    |
| 682 | BAMBOO_TRAPDOOR               | 3.127e-06 | 1.12e+07            | 0            | crafted    |
| 683 | BAMBOO_SIGN                   | 3.124e-06 | 1.12e+07            | 0            | crafted    |
| 684 | BAMBOO_MOSAIC_SLAB            | 3.088e-06 | 1.13e+07            | 0            | crafted    |
| 685 | BAMBOO_MOSAIC_STAIRS          | 3.088e-06 | 1.13e+07            | 0            | crafted    |
| 686 | BAMBOO_CHEST_RAFT             | 3.075e-06 | 1.14e+07            | 0            | crafted    |
| 687 | DIAMOND                       | 2.584e-06 | 1.35e+07            | 0            | drop       |
| 688 | DIAMOND_SPEAR                 | 2.579e-06 | 1.36e+07            | 0            | crafted    |
| 689 | DIAMOND_SHOVEL                | 2.579e-06 | 1.36e+07            | 0            | crafted    |
| 690 | GREEN_BED                     | 2.153e-06 | 1.62e+07            | 0            | crafted    |
| 691 | GREEN_WOOL_SLAB               | 2.106e-06 | 1.66e+07            | 0            | crafted    |
| 692 | GREEN_CUSHION                 | 2.106e-06 | 1.66e+07            | 0            | crafted    |
| 693 | JUKEBOX                       | 1.818e-06 | 1.92e+07            | 0            | crafted    |
| 694 | REDSTONE_BLOCK                | 1.775e-06 | 1.97e+07            | 0            | crafted    |
| 695 | DIAMOND_SWORD                 | 1.396e-06 | 2.5e+07             | 0            | crafted    |
| 696 | DIAMOND_HOE                   | 1.395e-06 | 2.51e+07            | 0            | crafted    |
| 697 | GREEN_BANNER                  | 1.305e-06 | 2.68e+07            | 0            | crafted    |
| 698 | GREEN_WOOL_STAIRS             | 1.305e-06 | 2.68e+07            | 0            | crafted    |
| 699 | DIAMOND_AXE                   | 1.004e-06 | 3.48e+07            | 0            | crafted    |
| 700 | DIAMOND_PICKAXE               | 1.004e-06 | 3.48e+07            | 0            | crafted    |
| 701 | DIAMOND_BOOTS                 | 8.11e-07  | 4.31e+07            | 0            | crafted    |
| 702 | DIAMOND_HELMET                | 6.969e-07 | 5.02e+07            | 0            | crafted    |
| 703 | DIAMOND_LEGGINGS              | 5.706e-07 | 6.13e+07            | 0            | crafted    |
| 704 | DIAMOND_CHESTPLATE            | 5.329e-07 | 6.56e+07            | 0            | crafted    |
| 705 | DIAMOND_BLOCK                 | 5.045e-07 | 6.93e+07            | 0            | crafted    |

## B. World spawn of the Desktop server

Sampled from the world spawn (tundra / frozen ocean).

| # | Item | Chance % | Times rarer than #1 | Smelt rounds | Source |
|---|---|---|---|---|---|
| 1 | DIRT | 31.34 | 1 | 0 | drop block |
| 2 | WATER | 13.94 | 2.25 | 0 | block |
| 3 | STONE | 11.48 | 2.73 | 1 | block |
| 4 | ICE | 6.317 | 4.96 | 0 | block |
| 5 | SAND | 3.48 | 9.01 | 0 | drop block |
| 6 | GRASS_BLOCK | 3.053 | 10.3 | 0 | block |
| 7 | SNOW | 2.068 | 15.2 | 0 | block |
| 8 | GRAVEL | 1.628 | 19.3 | 0 | drop block |
| 9 | COBBLESTONE | 1.584 | 19.8 | 0 | drop |
| 10 | STONE_BRICK_STAIRS | 1.531 | 20.5 | 1 | crafted |
| 11 | STONE_BRICK_WALL | 1.531 | 20.5 | 1 | crafted |
| 12 | STONE_BRICK_SLAB | 1.531 | 20.5 | 1 | crafted |
| 13 | CHISELED_STONE_BRICKS | 1.531 | 20.5 | 1 | crafted |
| 14 | STONE_BUTTON | 1.531 | 20.5 | 1 | crafted |
| 15 | SMOOTH_STONE_SLAB | 1.479 | 21.2 | 2 | crafted |
| 16 | SMOOTH_STONE | 1.479 | 21.2 | 2 | crafted |
| 17 | ANDESITE | 1.027 | 30.5 | 0 | drop block |
| 18 | GLASS | 0.975 | 32.1 | 1 | crafted |
| 19 | STONE_PRESSURE_PLATE | 0.8278 | 37.9 | 1 | crafted |
| 20 | GRANITE | 0.6449 | 48.6 | 0 | drop block |
| 21 | COBBLESTONE_SLAB | 0.6158 | 50.9 | 0 | crafted |
| 22 | SPRUCE_LEAVES | 0.6118 | 51.2 | 0 | block |
| 23 | STONE_SLAB | 0.5953 | 52.6 | 1 | crafted |
| 24 | STONE_BRICKS | 0.4806 | 65.2 | 1 | crafted |
| 25 | COARSE_DIRT | 0.4674 | 67 | 0 | drop block |
| 26 | CRACKED_STONE_BRICKS | 0.464 | 67.5 | 2 | crafted |
| 27 | COBBLESTONE_WALL | 0.3815 | 82.1 | 0 | crafted |
| 28 | COBBLESTONE_STAIRS | 0.3815 | 82.1 | 0 | crafted |
| 29 | GLASS_BOTTLE | 0.379 | 82.7 | 1 | crafted |
| 30 | DIORITE | 0.368 | 85.2 | 0 | drop block |
| 31 | STONE_STAIRS | 0.3561 | 88 | 2 | crafted |
| 32 | FURNACE | 0.3266 | 95.9 | 0 | crafted |
| 33 | SANDSTONE | 0.3241 | 96.7 | 0 | drop block |
| 34 | SMOOTH_SANDSTONE | 0.3081 | 102 | 1 | crafted |
| 35 | GLASS_PANE | 0.2267 | 138 | 2 | crafted |
| 36 | COAL | 0.1919 | 163 | 0 | drop |
| 37 | SANDSTONE_SLAB | 0.1772 | 177 | 0 | crafted |
| 38 | CHISELED_SANDSTONE | 0.1772 | 177 | 0 | crafted |
| 39 | SMOOTH_SANDSTONE_SLAB | 0.1713 | 183 | 1 | crafted |
| 40 | CLAY | 0.1707 | 184 | 0 | block |
| 41 | CUT_SANDSTONE_SLAB | 0.1641 | 191 | 0 | crafted |
| 42 | CUT_SANDSTONE | 0.1641 | 191 | 0 | crafted |
| 43 | ANDESITE_SLAB | 0.1629 | 192 | 0 | crafted |
| 44 | SANDSTONE_STAIRS | 0.155 | 202 | 0 | crafted |
| 45 | SANDSTONE_WALL | 0.155 | 202 | 0 | crafted |
| 46 | SMOOTH_SANDSTONE_STAIRS | 0.1446 | 217 | 2 | crafted |
| 47 | POLISHED_ANDESITE | 0.1333 | 235 | 0 | crafted |
| 48 | POLISHED_ANDESITE_SLAB | 0.1333 | 235 | 0 | crafted |
| 49 | COAL_ORE | 0.1304 | 240 | 0 | block |
| 50 | FEATHER | 0.1247 | 251 | 0 | drop |
| 51 | SPRUCE_LOG | 0.1101 | 285 | 0 | drop block |
| 52 | RABBIT | 0.1096 | 286 | 0 | drop |
| 53 | COOKED_RABBIT | 0.106 | 296 | 1 | crafted |
| 54 | ANDESITE_WALL | 0.1018 | 308 | 0 | crafted |
| 55 | ANDESITE_STAIRS | 0.1018 | 308 | 0 | crafted |
| 56 | GRANITE_SLAB | 0.1011 | 310 | 0 | crafted |
| 57 | CHICKEN | 0.09495 | 330 | 0 | drop |
| 58 | COOKED_CHICKEN | 0.09179 | 341 | 1 | crafted |
| 59 | POLISHED_ANDESITE_STAIRS | 0.08678 | 361 | 0 | crafted |
| 60 | POLISHED_GRANITE_SLAB | 0.0816 | 384 | 0 | crafted |
| 61 | POLISHED_GRANITE | 0.0816 | 384 | 0 | crafted |
| 62 | COPPER_ORE | 0.07338 | 427 | 0 | block |
| 63 | GRANITE_WALL | 0.06262 | 500 | 0 | crafted |
| 64 | GRANITE_STAIRS | 0.06262 | 500 | 0 | crafted |
| 65 | CLAY_BALL | 0.06171 | 508 | 0 | drop |
| 66 | BRICK | 0.05965 | 525 | 1 | crafted |
| 67 | POLISHED_GRANITE_STAIRS | 0.05362 | 584 | 0 | crafted |
| 68 | RABBIT_HIDE | 0.04453 | 704 | 0 | drop |
| 69 | COAL_BLOCK | 0.03747 | 836 | 0 | crafted |
| 70 | FERN | 0.03527 | 889 | 0 | block |
| 71 | BEEF | 0.03303 | 949 | 0 | drop |
| 72 | COOKED_BEEF | 0.03193 | 982 | 1 | crafted |
| 73 | SHORT_GRASS | 0.0279 | 1.12e+03 | 0 | block |
| 74 | LARGE_FERN | 0.02435 | 1.29e+03 | 0 | block |
| 75 | FLOWER_POT | 0.02319 | 1.35e+03 | 1 | crafted |
| 76 | DIORITE_SLAB | 0.02282 | 1.37e+03 | 0 | crafted |
| 77 | LEATHER | 0.02217 | 1.41e+03 | 0 | drop |
| 78 | BRICK_SLAB | 0.01872 | 1.67e+03 | 1 | crafted |
| 79 | BRICK_STAIRS | 0.01872 | 1.67e+03 | 1 | crafted |
| 80 | BRICK_WALL | 0.01872 | 1.67e+03 | 1 | crafted |
| 81 | TERRACOTTA | 0.01872 | 1.67e+03 | 1 | crafted |
| 82 | BRICKS | 0.01872 | 1.67e+03 | 1 | crafted |
| 83 | DECORATED_POT | 0.01872 | 1.67e+03 | 1 | crafted |
| 84 | POLISHED_DIORITE | 0.01843 | 1.7e+03 | 0 | crafted |
| 85 | POLISHED_DIORITE_SLAB | 0.01843 | 1.7e+03 | 0 | crafted |
| 86 | RAW_COPPER | 0.01569 | 2e+03 | 0 | drop |
| 87 | COPPER_INGOT | 0.01517 | 2.07e+03 | 1 | crafted |
| 88 | COPPER_NUGGET | 0.01517 | 2.07e+03 | 1 | crafted |
| 89 | DIORITE_STAIRS | 0.01414 | 2.22e+03 | 0 | crafted |
| 90 | DIORITE_WALL | 0.01414 | 2.22e+03 | 0 | crafted |
| 91 | TORCH | 0.01413 | 2.22e+03 | 0 | crafted |
| 92 | POLISHED_DIORITE_STAIRS | 0.01211 | 2.59e+03 | 0 | crafted |
| 93 | LEATHER_BOOTS | 0.009705 | 3.23e+03 | 0 | crafted |
| 94 | LEATHER_HELMET | 0.009086 | 3.45e+03 | 0 | crafted |
| 95 | BRUSH | 0.008845 | 3.54e+03 | 1 | crafted |
| 96 | LEATHER_LEGGINGS | 0.008497 | 3.69e+03 | 0 | crafted |
| 97 | LEATHER_HORSE_ARMOR | 0.008497 | 3.69e+03 | 0 | crafted |
| 98 | LEATHER_CHESTPLATE | 0.008346 | 3.75e+03 | 0 | crafted |
| 99 | COPPER_CHAIN | 0.0082 | 3.82e+03 | 1 | crafted |
| 100 | IRON_ORE | 0.00716 | 4.38e+03 | 0 | block |
| 101 | COPPER_TORCH | 0.00683 | 4.59e+03 | 1 | crafted |
| 102 | SPRUCE_PLANKS | 0.006154 | 5.09e+03 | 0 | drop block |
| 103 | ITEM_FRAME | 0.006131 | 5.11e+03 | 0 | crafted |
| 104 | SPRUCE_BUTTON | 0.00603 | 5.2e+03 | 0 | crafted |
| 105 | STONE_HOE | 0.006024 | 5.2e+03 | 0 | crafted |
| 106 | STONE_SWORD | 0.006024 | 5.2e+03 | 0 | crafted |
| 107 | LEVER | 0.006024 | 5.2e+03 | 0 | crafted |
| 108 | STONE_SHOVEL | 0.006024 | 5.2e+03 | 0 | crafted |
| 109 | STONE_SPEAR | 0.006024 | 5.2e+03 | 0 | crafted |
| 110 | STONE_PICKAXE | 0.006024 | 5.2e+03 | 0 | crafted |
| 111 | STONE_AXE | 0.006024 | 5.2e+03 | 0 | crafted |
| 112 | SPRUCE_PRESSURE_PLATE | 0.006023 | 5.2e+03 | 0 | crafted |
| 113 | STICK | 0.006023 | 5.2e+03 | 0 | crafted |
| 114 | SPRUCE_SLAB | 0.006021 | 5.2e+03 | 0 | crafted |
| 115 | BOWL | 0.006021 | 5.2e+03 | 0 | crafted |
| 116 | CRAFTING_TABLE | 0.006019 | 5.21e+03 | 0 | crafted |
| 117 | LIGHTNING_ROD | 0.005897 | 5.31e+03 | 1 | crafted |
| 118 | CHARCOAL | 0.005844 | 5.36e+03 | 1 | crafted |
| 119 | COPPER_SHOVEL | 0.005189 | 6.04e+03 | 1 | crafted |
| 120 | COPPER_SPEAR | 0.005189 | 6.04e+03 | 1 | crafted |
| 121 | COPPER_LANTERN | 0.004882 | 6.42e+03 | 1 | crafted |
| 122 | COPPER_TRAPDOOR | 0.004761 | 6.58e+03 | 1 | crafted |
| 123 | COPPER_BOOTS | 0.004761 | 6.58e+03 | 1 | crafted |
| 124 | LAPIS_ORE | 0.004567 | 6.86e+03 | 0 | block |
| 125 | FIREFLY_BUSH | 0.004242 | 7.39e+03 | 0 | drop block |
| 126 | COPPER_HOE | 0.004144 | 7.56e+03 | 1 | crafted |
| 127 | COPPER_SWORD | 0.004144 | 7.56e+03 | 1 | crafted |
| 128 | COPPER_HELMET | 0.003949 | 7.93e+03 | 2 | crafted |
| 129 | SPRUCE_FENCE | 0.003666 | 8.55e+03 | 0 | drop block |
| 130 | COPPER_DOOR | 0.003527 | 8.88e+03 | 2 | crafted |
| 131 | COPPER_BARS | 0.003527 | 8.88e+03 | 2 | crafted |
| 132 | COPPER_PICKAXE | 0.003497 | 8.96e+03 | 1 | crafted |
| 133 | COPPER_AXE | 0.003497 | 8.96e+03 | 1 | crafted |
| 134 | SPRUCE_STAIRS | 0.003386 | 9.25e+03 | 0 | drop block |
| 135 | WOODEN_SHOVEL | 0.003286 | 9.54e+03 | 0 | crafted |
| 136 | WOODEN_SPEAR | 0.003286 | 9.54e+03 | 0 | crafted |
| 137 | WOODEN_SWORD | 0.003284 | 9.54e+03 | 0 | crafted |
| 138 | WOODEN_HOE | 0.003284 | 9.54e+03 | 0 | crafted |
| 139 | WOODEN_AXE | 0.003283 | 9.54e+03 | 0 | crafted |
| 140 | WOODEN_PICKAXE | 0.003283 | 9.54e+03 | 0 | crafted |
| 141 | SPRUCE_DOOR | 0.003281 | 9.55e+03 | 0 | drop block |
| 142 | LADDER | 0.003256 | 9.62e+03 | 0 | crafted |
| 143 | SPRUCE_BOAT | 0.003255 | 9.63e+03 | 0 | crafted |
| 144 | COMPOSTER | 0.003255 | 9.63e+03 | 0 | crafted |
| 145 | SPRUCE_TRAPDOOR | 0.003255 | 9.63e+03 | 0 | crafted |
| 146 | CHEST | 0.003254 | 9.63e+03 | 0 | crafted |
| 147 | SPRUCE_FENCE_GATE | 0.003253 | 9.63e+03 | 0 | crafted |
| 148 | COPPER_LEGGINGS | 0.003234 | 9.69e+03 | 2 | crafted |
| 149 | GRINDSTONE | 0.003175 | 9.87e+03 | 1 | crafted |
| 150 | RAW_COPPER_BLOCK | 0.003064 | 1.02e+04 | 0 | crafted |
| 151 | ARMOR_STAND | 0.003039 | 1.03e+04 | 2 | crafted |
| 152 | COPPER_CHESTPLATE | 0.00302 | 1.04e+04 | 2 | crafted |
| 153 | CAMPFIRE | 0.002899 | 1.08e+04 | 0 | crafted |
| 154 | COPPER_BLOCK | 0.002757 | 1.14e+04 | 3 | crafted |
| 155 | CUT_COPPER_STAIRS | 0.002757 | 1.14e+04 | 3 | crafted |
| 156 | CHISELED_COPPER | 0.002757 | 1.14e+04 | 3 | crafted |
| 157 | COPPER_GRATE | 0.002757 | 1.14e+04 | 3 | crafted |
| 158 | CUT_COPPER_SLAB | 0.002757 | 1.14e+04 | 3 | crafted |
| 159 | CUT_COPPER | 0.002757 | 1.14e+04 | 3 | crafted |
| 160 | CHISELED_BOOKSHELF | 0.002355 | 1.33e+04 | 0 | crafted |
| 161 | BARREL | 0.002355 | 1.33e+04 | 0 | crafted |
| 162 | SPRUCE_SIGN | 0.002339 | 1.34e+04 | 0 | crafted |
| 163 | MAGMA_BLOCK | 0.002269 | 1.38e+04 | 0 | drop block |
| 164 | DIRT_PATH | 0.001997 | 1.57e+04 | 0 | block |
| 165 | SMOKER | 0.001897 | 1.65e+04 | 0 | crafted |
| 166 | SPRUCE_CHEST_BOAT | 0.001887 | 1.66e+04 | 0 | crafted |
| 167 | SPRUCE_WOOD | 0.001881 | 1.67e+04 | 0 | crafted |
| 168 | COPPER_CHEST | 0.001805 | 1.74e+04 | 2 | crafted |
| 169 | BUBBLE_COLUMN | 0.001446 | 2.17e+04 | 0 | block |
| 170 | POPPY | 0.001276 | 2.46e+04 | 0 | drop block |
| 171 | BLUE_CONCRETE_POWDER | 0.00115 | 2.73e+04 | 0 | crafted |
| 172 | BLACK_WOOL | 0.001147 | 2.73e+04 | 0 | drop |
| 173 | LAPIS_LAZULI | 0.00114 | 2.75e+04 | 0 | drop |
| 174 | BLUE_DYE | 0.00114 | 2.75e+04 | 0 | crafted |
| 175 | PAINTING | 0.001124 | 2.79e+04 | 0 | crafted |
| 176 | BROWN_MUSHROOM | 0.001096 | 2.86e+04 | 0 | drop block |
| 177 | BLACK_HARNESS | 0.001068 | 2.93e+04 | 1 | crafted |
| 178 | DANDELION | 0.001063 | 2.95e+04 | 0 | drop block |
| 179 | BLUE_STAINED_GLASS_PANE | 0.001062 | 2.95e+04 | 2 | crafted |
| 180 | BLUE_STAINED_GLASS | 0.001059 | 2.96e+04 | 2 | crafted |
| 181 | BLUE_TERRACOTTA | 0.0009624 | 3.26e+04 | 2 | crafted |
| 182 | BLUE_GLAZED_TERRACOTTA | 0.000928 | 3.38e+04 | 3 | crafted |
| 183 | SADDLE | 0.0009169 | 3.42e+04 | 1 | crafted |
| 184 | LANTERN | 0.0007538 | 4.16e+04 | 1 | drop block |
| 185 | RAW_IRON | 0.0007216 | 4.34e+04 | 0 | drop |
| 186 | IRON_NUGGET | 0.0006976 | 4.49e+04 | 1 | crafted |
| 187 | IRON_INGOT | 0.0006976 | 4.49e+04 | 1 | crafted |
| 188 | STONECUTTER | 0.0006969 | 4.5e+04 | 1 | crafted |
| 189 | BLUE_WOOL | 0.000662 | 4.73e+04 | 0 | crafted |
| 190 | BLUE_HARNESS | 0.0006555 | 4.78e+04 | 1 | crafted |
| 191 | LODESTONE | 0.0006482 | 4.83e+04 | 3 | crafted |
| 192 | IRON_SHOVEL | 0.0006299 | 4.98e+04 | 1 | crafted |
| 193 | IRON_SPEAR | 0.0006299 | 4.98e+04 | 1 | crafted |
| 194 | BLACK_CARPET | 0.0006199 | 5.06e+04 | 0 | crafted |
| 195 | TRIPWIRE_HOOK | 0.0006103 | 5.13e+04 | 1 | crafted |
| 196 | SHIELD | 0.0005978 | 5.24e+04 | 1 | crafted |
| 197 | TRAPPED_CHEST | 0.0005227 | 5.99e+04 | 1 | crafted |
| 198 | BLUE_CARPET | 0.0004683 | 6.69e+04 | 0 | crafted |
| 199 | BLACK_WOOL_SLAB | 0.0004458 | 7.03e+04 | 0 | crafted |
| 200 | BLACK_CUSHION | 0.0004458 | 7.03e+04 | 0 | crafted |
| 201 | BLACK_BED | 0.0004289 | 7.31e+04 | 0 | crafted |
| 202 | GLOW_LICHEN | 0.0004216 | 7.43e+04 | 0 | block |
| 203 | OAK_LOG | 0.0004034 | 7.77e+04 | 0 | drop block |
| 204 | SMITHING_TABLE | 0.0003831 | 8.18e+04 | 1 | crafted |
| 205 | SHEARS | 0.0003771 | 8.31e+04 | 1 | crafted |
| 206 | HEAVY_WEIGHTED_PRESSURE_PLATE | 0.0003771 | 8.31e+04 | 1 | crafted |
| 207 | IRON_CHAIN | 0.0003771 | 8.31e+04 | 1 | crafted |
| 208 | BLUE_CUSHION | 0.0003769 | 8.31e+04 | 0 | crafted |
| 209 | BLUE_WOOL_SLAB | 0.0003769 | 8.31e+04 | 0 | crafted |
| 210 | BLUE_BED | 0.0003682 | 8.51e+04 | 0 | crafted |
| 211 | IRON_HOE | 0.0003564 | 8.79e+04 | 1 | crafted |
| 212 | IRON_SWORD | 0.0003564 | 8.79e+04 | 1 | crafted |
| 213 | MUSHROOM_STEW | 0.0003191 | 9.82e+04 | 0 | crafted |
| 214 | BUSH | 0.0002881 | 1.09e+05 | 0 | block |
| 215 | STRIPPED_SPRUCE_LOG | 0.0002774 | 1.13e+05 | 0 | drop block |
| 216 | BLACK_WOOL_STAIRS | 0.0002762 | 1.13e+05 | 0 | crafted |
| 217 | BUCKET | 0.0002712 | 1.16e+05 | 1 | crafted |
| 218 | BLACK_BANNER | 0.0002696 | 1.16e+05 | 0 | crafted |
| 219 | BLUE_WOOL_STAIRS | 0.0002684 | 1.17e+05 | 0 | crafted |
| 220 | IRON_AXE | 0.0002603 | 1.2e+05 | 1 | crafted |
| 221 | IRON_PICKAXE | 0.0002603 | 1.2e+05 | 1 | crafted |
| 222 | ORANGE_GLAZED_TERRACOTTA | 0.0002505 | 1.25e+05 | 2.7 | drop block |
| 223 | ORANGE_TERRACOTTA | 0.0002397 | 1.31e+05 | 2 | drop block |
| 224 | BUNDLE | 0.0002358 | 1.33e+05 | 0 | crafted |
| 225 | STRING | 0.0002354 | 1.33e+05 | 0 | drop |
| 226 | WHITE_WOOL | 0.0002303 | 1.36e+05 | 0 | drop |
| 227 | WHITE_HARNESS | 0.000226 | 1.39e+05 | 1 | crafted |
| 228 | LAPIS_BLOCK | 0.0002226 | 1.41e+05 | 0 | crafted |
| 229 | IRON_BOOTS | 0.0002189 | 1.43e+05 | 1 | crafted |
| 230 | IRON_TRAPDOOR | 0.0002189 | 1.43e+05 | 1 | crafted |
| 231 | MINECART | 0.0001816 | 1.73e+05 | 2 | crafted |
| 232 | IRON_HELMET | 0.0001816 | 1.73e+05 | 2 | crafted |
| 233 | FURNACE_MINECART | 0.0001815 | 1.73e+05 | 2 | crafted |
| 234 | BLAST_FURNACE | 0.000175 | 1.79e+05 | 3 | crafted |
| 235 | MUD_BRICK_SLAB | 0.0001742 | 1.8e+05 | 0 | drop block |
| 236 | CHEST_MINECART | 0.0001724 | 1.82e+05 | 2 | crafted |
| 237 | HOPPER | 0.0001724 | 1.82e+05 | 2 | crafted |
| 238 | BLUE_BANNER | 0.0001662 | 1.89e+05 | 0 | crafted |
| 239 | IRON_BARS | 0.0001622 | 1.93e+05 | 2 | crafted |
| 240 | IRON_DOOR | 0.0001622 | 1.93e+05 | 2 | crafted |
| 241 | PURPLE_HARNESS | 0.0001611 | 1.95e+05 | 1 | crafted |
| 242 | RAIL | 0.0001581 | 1.98e+05 | 2 | crafted |
| 243 | PURPLE_WOOL | 0.0001505 | 2.08e+05 | 0 | crafted |
| 244 | CAULDRON | 0.0001487 | 2.11e+05 | 2 | crafted |
| 245 | IRON_LEGGINGS | 0.0001487 | 2.11e+05 | 2 | crafted |
| 246 | PURPLE_CARPET | 0.0001475 | 2.12e+05 | 0 | crafted |
| 247 | INFESTED_STONE | 0.0001472 | 2.13e+05 | 0 | block |
| 248 | SUSPICIOUS_STEW | 0.0001462 | 2.14e+05 | 0 | crafted |
| 249 | SUGAR_CANE | 0.0001452 | 2.16e+05 | 0 | drop block |
| 250 | PURPLE_WOOL_SLAB | 0.0001429 | 2.19e+05 | 0 | crafted |
| 251 | PURPLE_BED | 0.0001416 | 2.21e+05 | 0 | crafted |
| 252 | YELLOW_HARNESS | 0.0001413 | 2.22e+05 | 1 | crafted |
| 253 | RAW_IRON_BLOCK | 0.0001409 | 2.22e+05 | 0 | crafted |
| 254 | YELLOW_WOOL | 0.0001398 | 2.24e+05 | 0 | crafted |
| 255 | IRON_CHESTPLATE | 0.0001389 | 2.26e+05 | 2 | crafted |
| 256 | RED_HARNESS | 0.000137 | 2.29e+05 | 1 | crafted |
| 257 | YELLOW_CARPET | 0.0001367 | 2.29e+05 | 0 | crafted |
| 258 | RED_CARPET | 0.0001354 | 2.31e+05 | 0 | crafted |
| 259 | RED_WOOL | 0.0001316 | 2.38e+05 | 0 | crafted |
| 260 | RED_WOOL_SLAB | 0.0001314 | 2.39e+05 | 0 | crafted |
| 261 | RED_CUSHION | 0.0001314 | 2.39e+05 | 0 | crafted |
| 262 | YELLOW_WOOL_SLAB | 0.0001313 | 2.39e+05 | 0 | crafted |
| 263 | YELLOW_CUSHION | 0.0001313 | 2.39e+05 | 0 | crafted |
| 264 | PURPLE_WOOL_STAIRS | 0.0001312 | 2.39e+05 | 0 | crafted |
| 265 | YELLOW_BED | 0.0001299 | 2.41e+05 | 0 | crafted |
| 266 | RED_BED | 0.0001299 | 2.41e+05 | 0 | crafted |
| 267 | IRON_BLOCK | 0.0001268 | 2.47e+05 | 3 | crafted |
| 268 | FISHING_ROD | 0.0001258 | 2.49e+05 | 0 | crafted |
| 269 | LOOM | 0.0001258 | 2.49e+05 | 0 | crafted |
| 270 | WHITE_CARPET | 0.000125 | 2.51e+05 | 0 | crafted |
| 271 | RED_WOOL_STAIRS | 0.0001208 | 2.59e+05 | 0 | crafted |
| 272 | PURPLE_CUSHION | 0.0001195 | 2.62e+05 | 0 | crafted |
| 273 | YELLOW_WOOL_STAIRS | 0.0001194 | 2.63e+05 | 0 | crafted |
| 274 | HOPPER_MINECART | 0.0001174 | 2.67e+05 | 3 | crafted |
| 275 | YELLOW_CONCRETE_POWDER | 0.0001167 | 2.68e+05 | 0 | crafted |
| 276 | YELLOW_DYE | 0.0001161 | 2.7e+05 | 0 | crafted |
| 277 | STRIPPED_SPRUCE_WOOD | 0.0001139 | 2.75e+05 | 0 | drop block |
| 278 | YELLOW_TERRACOTTA | 0.0001091 | 2.87e+05 | 2 | crafted |
| 279 | YELLOW_STAINED_GLASS_PANE | 0.0001084 | 2.89e+05 | 2 | crafted |
| 280 | YELLOW_STAINED_GLASS | 0.0001084 | 2.89e+05 | 2 | crafted |
| 281 | YELLOW_GLAZED_TERRACOTTA | 0.0001052 | 2.98e+05 | 3 | crafted |
| 282 | CROSSBOW | 9.614e-05 | 3.26e+05 | 1 | crafted |
| 283 | PURPLE_CONCRETE_POWDER | 9.554e-05 | 3.28e+05 | 0 | crafted |
| 284 | RED_CONCRETE_POWDER | 9.549e-05 | 3.28e+05 | 0 | crafted |
| 285 | WHITE_WOOL_SLAB | 9.528e-05 | 3.29e+05 | 0 | crafted |
| 286 | WHITE_CUSHION | 9.528e-05 | 3.29e+05 | 0 | crafted |
| 287 | PURPLE_DYE | 9.492e-05 | 3.3e+05 | 0 | crafted |
| 288 | RED_DYE | 9.457e-05 | 3.31e+05 | 0 | crafted |
| 289 | WHITE_BED | 9.402e-05 | 3.33e+05 | 0 | crafted |
| 290 | RED_TERRACOTTA | 9.092e-05 | 3.45e+05 | 2 | crafted |
| 291 | BOW | 9.074e-05 | 3.45e+05 | 0 | crafted |
| 292 | PURPLE_TERRACOTTA | 8.99e-05 | 3.49e+05 | 2 | crafted |
| 293 | LAVA | 8.911e-05 | 3.52e+05 | 0 | block |
| 294 | PURPLE_STAINED_GLASS_PANE | 8.87e-05 | 3.53e+05 | 2 | crafted |
| 295 | PURPLE_STAINED_GLASS | 8.865e-05 | 3.53e+05 | 2 | crafted |
| 296 | RED_STAINED_GLASS_PANE | 8.845e-05 | 3.54e+05 | 2 | crafted |
| 297 | RED_STAINED_GLASS | 8.839e-05 | 3.55e+05 | 2 | crafted |
| 298 | RED_GLAZED_TERRACOTTA | 8.767e-05 | 3.57e+05 | 3 | crafted |
| 299 | PURPLE_GLAZED_TERRACOTTA | 8.669e-05 | 3.61e+05 | 3 | crafted |
| 300 | ORANGE_BED | 7.99e-05 | 3.92e+05 | 0 | crafted |
| 301 | ANVIL | 7.99e-05 | 3.92e+05 | 8 | crafted |
| 302 | ORANGE_WOOL_STAIRS | 7.623e-05 | 4.11e+05 | 0 | crafted |
| 303 | ORANGE_WOOL_SLAB | 7.589e-05 | 4.13e+05 | 0 | crafted |
| 304 | ORANGE_CARPET | 7.305e-05 | 4.29e+05 | 0 | crafted |
| 305 | ORANGE_HARNESS | 7.205e-05 | 4.35e+05 | 1 | crafted |
| 306 | ORANGE_WOOL | 7.082e-05 | 4.43e+05 | 0 | crafted |
| 307 | ORANGE_CUSHION | 6.986e-05 | 4.49e+05 | 0 | crafted |
| 308 | WHITE_WOOL_STAIRS | 6.826e-05 | 4.59e+05 | 0 | crafted |
| 309 | WHITE_BANNER | 6.753e-05 | 4.64e+05 | 0 | crafted |
| 310 | LEAD | 6.347e-05 | 4.94e+05 | 0 | crafted |
| 311 | RED_MUSHROOM | 5.483e-05 | 5.72e+05 | 0 | drop block |
| 312 | ORANGE_CONCRETE_POWDER | 5.349e-05 | 5.86e+05 | 0 | crafted |
| 313 | ORANGE_DYE | 5.33e-05 | 5.88e+05 | 0 | crafted |
| 314 | ORANGE_STAINED_GLASS_PANE | 4.978e-05 | 6.29e+05 | 2 | crafted |
| 315 | ORANGE_STAINED_GLASS | 4.977e-05 | 6.3e+05 | 2 | crafted |
| 316 | CYAN_TERRACOTTA | 4.712e-05 | 6.65e+05 | 0 | drop block |
| 317 | SUGAR | 4.401e-05 | 7.12e+05 | 0 | crafted |
| 318 | EMERALD | 4.312e-05 | 7.27e+05 | 0 | drop |
| 319 | YELLOW_BANNER | 3.567e-05 | 8.78e+05 | 0 | crafted |
| 320 | PURPLE_BANNER | 3.5e-05 | 8.95e+05 | 0 | crafted |
| 321 | OAK_SLAB | 3.334e-05 | 9.4e+05 | 0 | crafted |
| 322 | OAK_BUTTON | 3.334e-05 | 9.4e+05 | 0 | crafted |
| 323 | OAK_PRESSURE_PLATE | 3.334e-05 | 9.4e+05 | 0 | crafted |
| 324 | OAK_PLANKS | 3.334e-05 | 9.4e+05 | 0 | crafted |
| 325 | OAK_FENCE_GATE | 3.317e-05 | 9.45e+05 | 0 | crafted |
| 326 | OAK_FENCE | 3.317e-05 | 9.45e+05 | 0 | crafted |
| 327 | WHITE_TERRACOTTA | 3.157e-05 | 9.93e+05 | 0 | drop block |
| 328 | RED_BANNER | 3.155e-05 | 9.93e+05 | 0 | crafted |
| 329 | EMERALD_ORE | 2.82e-05 | 1.11e+06 | 0 | block |
| 330 | FLETCHING_TABLE | 2.732e-05 | 1.15e+06 | 0 | drop block |
| 331 | CYAN_GLAZED_TERRACOTTA | 2.194e-05 | 1.43e+06 | 1 | crafted |
| 332 | OAK_STAIRS | 1.802e-05 | 1.74e+06 | 0 | crafted |
| 333 | OAK_TRAPDOOR | 1.802e-05 | 1.74e+06 | 0 | crafted |
| 334 | OAK_DOOR | 1.802e-05 | 1.74e+06 | 0 | crafted |
| 335 | OAK_BOAT | 1.802e-05 | 1.74e+06 | 0 | crafted |
| 336 | OAK_SIGN | 1.797e-05 | 1.74e+06 | 0 | crafted |
| 337 | OAK_CHEST_BOAT | 1.793e-05 | 1.75e+06 | 0 | crafted |
| 338 | PAPER | 1.711e-05 | 1.83e+06 | 0 | crafted |
| 339 | BOOK | 1.71e-05 | 1.83e+06 | 0 | crafted |
| 340 | ORANGE_BANNER | 1.677e-05 | 1.87e+06 | 0 | crafted |
| 341 | FIELD_MASONED_BANNER_PATTERN | 1.652e-05 | 1.9e+06 | 1 | crafted |
| 342 | NAME_TAG | 1.648e-05 | 1.9e+06 | 1 | crafted |
| 343 | CARTOGRAPHY_TABLE | 1.638e-05 | 1.91e+06 | 0 | crafted |
| 344 | WHITE_GLAZED_TERRACOTTA | 1.47e-05 | 2.13e+06 | 1 | crafted |
| 345 | SUSPICIOUS_GRAVEL | 1.058e-05 | 2.96e+06 | 0 | block |
| 346 | OAK_WOOD | 1.046e-05 | 3e+06 | 0 | crafted |
| 347 | EMERALD_BLOCK | 8.417e-06 | 3.72e+06 | 0 | crafted |
| 348 | BOOKSHELF | 8.237e-06 | 3.8e+06 | 0 | crafted |
| 349 | LECTERN | 8.067e-06 | 3.88e+06 | 0 | crafted |
| 350 | SPRUCE_SHELF | 3.042e-06 | 1.03e+07 | 0 | crafted |
| 351 | SPRUCE_HANGING_SIGN | 2.919e-06 | 1.07e+07 | 1 | crafted |

## C. What smelting time changed

Ranks and chances are before (no smelting time, the previous ranking) → after. Ratio is after / before; A is pooled over the 7 biomes, B is the world spawn. Everything is renormalised, so a ratio of 1.00 can still hide a small gain or loss.

### Hardest hit (largest drop in chance, A, items above 0.0001 %)

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| ANVIL | 8 | 496 → 517 | 0.0001797 → 0.0001384 | 0.770 | 282 → 301 | 0.769 |
| NETHER_BRICK_FENCE | 5 | 327 → 334 | 0.001427 → 0.001229 | 0.862 | – | – |
| DRIED_KELP_BLOCK | 3 | 307 → 311 | 0.001768 → 0.001631 | 0.922 | – | – |
| YELLOW_GLAZED_TERRACOTTA | 3 | 500 → 507 | 0.000176 → 0.0001631 | 0.927 | 277 → 281 | 0.931 |
| ORANGE_GLAZED_TERRACOTTA | 3 | 527 → 536 | 0.0001112 → 0.0001033 | 0.929 | 222 → 222 | 0.986 |
| RED_GLAZED_TERRACOTTA | 3 | 516 → 519 | 0.0001453 → 0.0001351 | 0.930 | 287 → 298 | 0.933 |
| LIGHT_GRAY_GLAZED_TERRACOTTA | 3 | 519 → 522 | 0.0001382 → 0.0001286 | 0.930 | – | – |
| GREEN_TERRACOTTA | 3 | 509 → 512 | 0.0001604 → 0.0001493 | 0.931 | – | – |
| GREEN_GLAZED_TERRACOTTA | 3 | 510 → 511 | 0.0001604 → 0.0001493 | 0.931 | – | – |
| PINK_GLAZED_TERRACOTTA | 3 | 511 → 513 | 0.0001552 → 0.0001445 | 0.931 | – | – |
| BLUE_GLAZED_TERRACOTTA | 3 | 517 → 520 | 0.0001446 → 0.0001348 | 0.932 | 182 → 182 | 0.934 |
| ARMOR_STAND | 2 | 109 → 115 | 0.03742 → 0.03509 | 0.938 | 145 → 151 | 0.937 |

### High smelting (3 or more rounds), most likely first

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| DRIED_KELP_BLOCK | 3 | 307 → 311 | 0.001768 → 0.001631 | 0.922 | – | – |
| NETHER_BRICK_FENCE | 5 | 327 → 334 | 0.001427 → 0.001229 | 0.862 | – | – |
| LODESTONE | 3.9 | 340 → 349 | 0.00118 → 0.001108 | 0.939 | 189 → 191 | 0.943 |
| COPPER_BLOCK | 3 | 367 → 370 | 0.0009542 → 0.0008979 | 0.941 | 152 → 154 | 0.943 |
| COPPER_GRATE | 3 | 369 → 371 | 0.0009542 → 0.0008979 | 0.941 | 156 → 157 | 0.943 |
| CUT_COPPER | 3 | 370 → 372 | 0.0009542 → 0.0008979 | 0.941 | 158 → 159 | 0.943 |
| CUT_COPPER_STAIRS | 3 | 371 → 373 | 0.0009542 → 0.0008979 | 0.941 | 154 → 155 | 0.943 |
| CHISELED_COPPER | 3 | 372 → 374 | 0.0009542 → 0.0008979 | 0.941 | 155 → 156 | 0.943 |
| CUT_COPPER_SLAB | 3 | 373 → 375 | 0.0009542 → 0.0008979 | 0.941 | 157 → 158 | 0.943 |
| GREEN_STAINED_GLASS | 3 | 386 → 393 | 0.0007812 → 0.0007402 | 0.948 | – | – |
| BLAST_FURNACE | 3 | 460 → 466 | 0.0003201 → 0.0003024 | 0.945 | 234 → 234 | 0.943 |
| IRON_BLOCK | 3 | 485 → 486 | 0.0002324 → 0.0002196 | 0.945 | 258 → 267 | 0.943 |
| HOPPER_MINECART | 3 | 491 → 495 | 0.0001982 → 0.0001868 | 0.943 | 271 → 274 | 0.943 |
| ANVIL | 8 | 496 → 517 | 0.0001797 → 0.0001384 | 0.770 | 282 → 301 | 0.769 |

### Low smelting (one round or less), most likely first

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| STONE | 1 | 2 → 2 | 10.29 → 10.29 | 1.000 | 3 → 3 | 1.000 |
| STONE_BUTTON | 1 | 14 → 12 | 1.109 → 1.111 | 1.002 | 16 → 14 | 1.001 |
| STONE_BRICK_SLAB | 1 | 15 → 15 | 0.9769 → 0.9783 | 1.001 | 12 → 12 | 1.001 |
| CHISELED_STONE_BRICKS | 1 | 16 → 16 | 0.9753 → 0.9767 | 1.001 | 15 → 13 | 1.001 |
| GLASS | 1 | 20 → 20 | 0.7079 → 0.6995 | 0.988 | 18 → 18 | 0.986 |
| SMOOTH_SANDSTONE | 1 | 22 → 22 | 0.6382 → 0.6257 | 0.980 | 34 → 34 | 0.986 |
| STONE_PRESSURE_PLATE | 1 | 23 → 23 | 0.5997 → 0.6007 | 1.002 | 19 → 19 | 1.001 |
| STONE_SLAB | 1 | 26 → 26 | 0.4313 → 0.432 | 1.002 | 23 → 23 | 1.001 |
| STONE_BRICKS | 1 | 30 → 30 | 0.3499 → 0.3505 | 1.002 | 24 → 24 | 1.001 |
| SMOOTH_SANDSTONE_SLAB | 1 | 37 → 39 | 0.2855 → 0.2802 | 0.981 | 37 → 39 | 0.986 |
| GLASS_BOTTLE | 1 | 41 → 41 | 0.2752 → 0.2719 | 0.988 | 27 → 29 | 0.986 |
| COOKED_CHICKEN | 1 | 59 → 58 | 0.1729 → 0.17 | 0.983 | 58 → 58 | 0.983 |
| COOKED_BEEF | 1 | 69 → 70 | 0.1008 → 0.09909 | 0.983 | 72 → 72 | 0.983 |
| COOKED_PORKCHOP | 1 | 71 → 71 | 0.09968 → 0.09799 | 0.983 | – | – |

### Stone family

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| STONE | 1 | 2 → 2 | 10.29 → 10.29 | 1.000 | 3 → 3 | 1.000 |
| STONE_BRICKS | 1 | 30 → 30 | 0.3499 → 0.3505 | 1.002 | 24 → 24 | 1.001 |
| CRACKED_STONE_BRICKS | 2 | 31 → 31 | 0.3494 → 0.3379 | 0.967 | 25 → 26 | 0.967 |
| SMOOTH_STONE | 2 | 13 → 14 | 1.109 → 1.073 | 0.967 | 14 → 16 | 0.967 |
| SMOOTH_STONE_SLAB | 2 | 12 → 13 | 1.112 → 1.075 | 0.967 | 13 → 15 | 0.967 |

### Sand family

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| SAND | 0 | 8 → 8 | 2.243 → 2.259 | 1.007 | 5 → 5 | 1.006 |
| GLASS | 1 | 20 → 20 | 0.7079 → 0.6995 | 0.988 | 18 → 18 | 0.986 |
| GLASS_PANE | 2 | 56 → 59 | 0.1774 → 0.1696 | 0.956 | 35 → 35 | 0.952 |
| SMOOTH_SANDSTONE | 1 | 22 → 22 | 0.6382 → 0.6257 | 0.980 | 34 → 34 | 0.986 |

### Meat

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| BEEF | 0 | 68 → 68 | 0.1008 → 0.1025 | 1.017 | 71 → 71 | 1.017 |
| COOKED_BEEF | 1 | 69 → 70 | 0.1008 → 0.09909 | 0.983 | 72 → 72 | 0.983 |
| CHICKEN | 0 | 58 → 56 | 0.1729 → 0.1759 | 1.017 | 57 → 57 | 1.017 |
| COOKED_CHICKEN | 1 | 59 → 58 | 0.1729 → 0.17 | 0.983 | 58 → 58 | 0.983 |
| PORKCHOP | 0 | 70 → 69 | 0.09968 → 0.1014 | 1.017 | – | – |
| COOKED_PORKCHOP | 1 | 71 → 71 | 0.09968 → 0.09799 | 0.983 | – | – |

### Iron

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| RAW_IRON | 0 | 336 → 333 | 0.00119 → 0.00125 | 1.050 | 185 → 185 | 1.048 |
| IRON_NUGGET | 1 | 337 → 336 | 0.00119 → 0.001208 | 1.015 | 186 → 186 | 1.013 |
| IRON_INGOT | 1 | 338 → 337 | 0.00119 → 0.001208 | 1.015 | 187 → 187 | 1.013 |
| IRON_BARS | 2 | 465 → 464 | 0.0003164 → 0.0003106 | 0.982 | 238 → 239 | 0.978 |
| BUCKET | 1 | 434 → 430 | 0.0004628 → 0.0004696 | 1.015 | 218 → 217 | 1.013 |
| IRON_BLOCK | 3 | 485 → 486 | 0.0002324 → 0.0002196 | 0.945 | 258 → 267 | 0.943 |
| ANVIL | 8 | 496 → 517 | 0.0001797 → 0.0001384 | 0.770 | 282 → 301 | 0.769 |
| BLAST_FURNACE | 3 | 460 → 466 | 0.0003201 → 0.0003024 | 0.945 | 234 → 234 | 0.943 |
| HOPPER | 2 | 472 → 474 | 0.0002743 → 0.0002676 | 0.976 | 236 → 237 | 0.977 |
| CAULDRON | 2 | 456 → 460 | 0.0003221 → 0.0003168 | 0.983 | 243 → 244 | 0.978 |

### Clay

| Item | Smelt rounds | Rank before → after (A) | Chance % before → after (A) | Chance ratio (A) | Rank before → after (B) | Chance ratio (B) |
|---|---|---|---|---|---|---|
| CLAY_BALL | 0 | 262 → 262 | 0.003194 → 0.003293 | 1.031 | 65 → 65 | 1.024 |
| BRICK | 1 | 263 → 263 | 0.003194 → 0.003184 | 0.997 | 66 → 66 | 0.990 |
| TERRACOTTA | 1 | 360 → 359 | 0.001002 → 0.000999 | 0.997 | 81 → 81 | 0.990 |
| WHITE_GLAZED_TERRACOTTA | 3 | 612 → 616 | 1.871e-05 → 1.742e-05 | 0.931 | 344 → 344 | 0.983 |
| YELLOW_GLAZED_TERRACOTTA | 3 | 500 → 507 | 0.000176 → 0.0001631 | 0.927 | 277 → 281 | 0.931 |

