package wsg.lol.common.enums.match;

import wsg.lol.dao.common.serialize.JSONSerializable;

/**
 * Enum for queues of matches.
 *
 * @author Kingen
 */
public enum MatchQueueEnum implements JSONSerializable<Integer> {
    DeprecatedBlindPickGames_5v5(2),
    DeprecatedRankedSoloGames_5v5(4),
    DeprecatedRankedPreMadeGames_5v5(6),
    DeprecatedCoopVsAIGames_Rift(7),
    DeprecatedNormalGames_3v3(8),
    DeprecatedRankedFlexGames_3v3(9),
    DeprecatedDraftPickGames_5v5(14),
    DeprecatedDominionBlindPickGames_5v5(16),
    DeprecatedDominionDraftPickGames_5v5(17),
    DeprecatedDominionCoopVsAIGames(25),
    DeprecatedCoopVsAIIntroBotGames(31),
    DeprecatedCoopVsAIBeginnerBotGames(32),
    DeprecatedCoopVsAIIntermediateBotGames(33),
    DeprecatedRankedTeamGames_3v3(41),
    DeprecatedRankedTeamGames_5v5(42),
    DeprecatedCoopVsAIGames_Treeline(52),
    DeprecatedTeamBuilderGames_5v5(61),
    DeprecatedARAMGames_5v5(65),
    DeprecatedARAMCoopVsAIGames(67),
    DeprecatedOneforAllGames(70),
    SnowdownShowdownGames_1v1(72),
    SnowdownShowdownGames_2v2(73),
    HexakillGames_6v6_Rift(75),
    UltraRapidFireGames(76),
    OneForAllMirrorModeGames(78),
    CoopVsAIUltraRapidFireGames(83),
    DeprecatedDoomBotsRank1Games(91),
    DeprecatedDoomBotsRank2Games(92),
    DeprecatedDoomBotsRank5Games(93),
    DeprecatedAscensionGames(96),
    HexakillGames_6v6_Treeline(98),
    ARAMGames_5v5_Bridge(100),
    DeprecatedLegendofthePoroKingGames(300),
    NemesisGames(310),
    BlackMarketBrawlersGames(313),
    DeprecatedNexusSiegeGames(315),
    DefinitelyNotDominionGames(317),
    DeprecatedARURFGames(318),
    AllRandomGames(325),
    DraftPickGames_5v5(400),
    DeprecatedRankedDynamicGames_5v5(410),
    RankedSoloGames_5v5(420),
    BlindPickGames_5v5(430),
    RankedFlexGames_5v5(440),
    ARAMGames_5v5_Abyss(450),
    BlindPickGames_3v3(460),
    RankedFlexGames_3v3(470),
    BloodHuntAssassinGames(600),
    DarkStarSingularityGames(610),
    ClashGames(700),
    CoopVsAIIntermediateBotGames_Treeline(800),
    CoopVsAIIntroBotGames_Treeline(810),
    CoopVsAIBeginnerBotGames_Treeline(820),
    CoopVsAIIntroBotGames_Rift(830),
    CoopVsAIBeginnerBotGames_Rift(840),
    CoopVsAIIntermediateBotGames_Rift(850),
    ARURFGames(900),
    AscensionGames(910),
    LegendofthePoroKingGames(920),
    NexusSiegeGames(940),
    DoomBotsVotingGames(950),
    DoomBotsStandardGames(960),
    StarGuardianInvasionNormalGames(980),
    StarGuardianInvasionOnslaughtGames(990),
    PROJECTHuntersGames(1000),
    SnowARURFGames(1010),
    OneForAllGames(1020),
    OdysseyExtractionIntroGames(1030),
    OdysseyExtractionCadetGames(1040),
    OdysseyExtractionCrewMemberGames(1050),
    OdysseyExtractionCaptainGames(1060),
    OdysseyExtractionOnslaughtGames(1070),
    NexusBlitzGames(1200),
    UNKNOWN2000(2000),
    UNKNOWN2010(2010),
    ;

    private int value;

    MatchQueueEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer serialize() {
        return value;
    }
}
