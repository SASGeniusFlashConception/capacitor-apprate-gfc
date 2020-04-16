import {INIT, STATUT_APP, STATUT_BTN, THEME} from "./enum";

export interface APPRATE_OPTS {
    // Nom de l'application
    appName: string;
    // Id de l'application - com.exemple.appname
    appPackageId: string;
    // Theme de la popup
    theme: THEME
    // Titre de la popup d'évaluation - le texte %@% sera remplacé par le nom de l'application
    titleAppRate: string;
    // Message de la popup d'évaluation - le texte %@% sera remplacé par le nom de l'application
    messageAppRate: string;
    // Nombre d'execution de l'application avant ouverture de la popup d'évaluation
    untilPrompt: number;
    // Nombre de jour avant ouverture de la popup d'évaluation
    daysUntilPrompt: number;
    // Style du bouton positif
    positifBtnStyle: BUTTON_STYLE;
    // Style du bouton neutre
    netralBtnStyle: BUTTON_STYLE;
    // Style du bouton négatif
    negatifBtnStyle: BUTTON_STYLE;
}

export interface BUTTON_STYLE {
    text: string;
    color: string; //Default #000000
}

export interface APPRATE_INIT {
    appRateInit: INIT
}

export interface APP_PUBLISH_STATUT {
    appOnPlay: STATUT_APP
}

export interface APPRATE_STATUT {
    rateStatut: STATUT_BTN
}
