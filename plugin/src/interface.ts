import {MARKET_URL, STATUT_BTN} from "./enum";

export interface APPRATE_OPTS {
    appName: string;
    appPackageId: string;
    storeUrl: MARKET_URL;
    titleAppRate: string;
    messageAppRate: string;
    untilPrompt: number;
    daysUntilPrompt: number;
    positifButtonStyle: BUTTON_STYLE;
    netralButtonStyle: BUTTON_STYLE;
    negatifButtonStyle: BUTTON_STYLE;
    darkMode?: boolean;
    addAppIcon?: boolean;
    backgroundColor?: string;
}

export interface APPRATE_OPTS_CHECK {
    appPackageId: string;
    storeUrl: MARKET_URL;
}

export interface BUTTON_STYLE {
    buttonActionText: string;
    buttonActionColor?: string;
}

export interface APPRATE_INIT {
    appRateInit: boolean;
}

export interface APP_PUBLISH_STATUT {
    appOnPlay: boolean
}

export interface APPRATE_STATUT {
    rateStatut: STATUT_BTN
}
