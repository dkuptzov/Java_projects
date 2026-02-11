//package ru.crawl.domain.model;
//
//import java.util.List;
//
//public class Elixir extends Item {
//    public enum ElixirType {
//        Strength,
//        Dexterity,
//        MaxHP
//    }
//
//    private final ElixirType elixirType;
//
//    public Elixir(ElixirType elixirType, int level, int x, int y) {
//        super(TypeItem.ELIXIR, level, x, y, 100);
//        this.elixirType = elixirType;
//    }
//
//    public void PlayerEffect(Player player) {
//        switch (elixirType) {
//            case Strength: player.setStrength(1);
//            case Dexterity: player.setDex(1);
//            case MaxHP: { player.setMaxHp(10); player.setHp(10); }
//        }
//    }
//}
