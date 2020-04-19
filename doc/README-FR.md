# AppRateGFC - Android

[![Version Npm](https://img.shields.io/npm/v/capacitor-apprate-gfc)](https://www.npmjs.com/package/capacitor-apprate-gfc)

> Le plugin **capacitor-apprate-gfc** est une implementation native qui permet aux utilisateurs d'évaluer facilement votre application.
> Vous pouvez maintenant utiliser ce package comme plugin [Ionic Capacitor](https://capacitor.ionicframework.com) dans votre application.

## Soutenez nos développements

> Votre don nous permettra de développer plus de plugin open source mais également à maintenir ceux déjà publiés pour garantir une compatibilité avec les futures versions d'android, d'ios et de capacitor.
> Merci de votre soutien ! 

❤ [Je fais un don](https://paypal.me/GFCPAYPAL) ❤

## Sommaire

1. [Plateformes prise en charge](#1-plateformes-prise-en-charge) 
2. [Installation](#2-installation)  
    2.1 [Récuperation du package sur npm](#21-r%C3%A9cuperation-du-package-sur-npm)  
    2.2 [Ajout du plugin dans votre MainActivity.java](#22-ajout-du-plugin-dans-votre-mainactivityjava)   
3. [Screenshot](#3-screenshot) 
4. [Méthodes du plugin](#4-m%C3%A9thodes-du-plugin) 
5. [Interfaces du plugin](#5-interfaces-du-plugin)  
    5.1 [APPRATE_OPTS](#51-apprate_opts)   
    5.2 [APPRATE_OPTS_CHECK](#52-apprate_opts_check)  
    5.3 [BUTTON_STYLE](#53-button_style)        
    5.4 [APPRATE_INIT](#54-apprate_init)        
    5.5 [APP_PUBLISH_STATUT](#55-app_publish_statut)        
    5.6 [APPRATE_STATUT](#56-apprate_statut)        
6. [Enums du plugin](#6-enums-du-plugin)   
    6.1 [MARKET_URL](#61-market_url)   
    6.2 [STATUT_BTN](#62-statut_btn)  
7. [Utilisation du plugin](#7-utilisation-du-plugin)  
    7.1 [Intégration du plugin](#71-int%C3%A9gration-du-plugin)  
    7.2 [Méthode init(options: APPRATE_OPTS)](#72-m%C3%A9thode-initoptions-apprate_opts)  
    7.3 [Méthode checkAppStatus(options: APPRATE_OPTS_CHECK)](#73-m%C3%A9thode-checkappstatusoptions-apprate_opts_check)  
    7.4 [Méthode showDirectly()](#74-m%C3%A9thode-showdirectly)  
    7.5 [Méthode addListener('positifRateEvent')](#75-m%C3%A9thode-addlistenerpositifrateevent)  
    7.6 [Méthode addListener('netralRateEvent')](#76-m%C3%A9thode-addlistenernetralrateevent)  
    7.7 [Méthode addListener('negativeRateEvent')](#77-m%C3%A9thode-addlistenernegativerateevent)  
    7.8 [Méthode addListener('appIsPublishEvent')](#78-m%C3%A9thode-addlistenerappispublishevent)  
    7.9 [Méthode addListener('isAllReadyShow')](#79-m%C3%A9thode-addlistenerisallreadyshow)  
8. [Ajouter une icone dans la popup](#8-ajouter-une-icone-dans-la-popup) 
9. [Demo](#9-demo-du-plugin) 
10. [Nos autres plugins](#10-nos-autres-plugins) 


## 1. Plateformes prise en charge

- [x] Android

## 2. Installation

### 2.1 Récuperation du package sur npm

``` npm install capacitor-apprate-gfc --save ```

### 2.2 Ajout du plugin dans votre MainActivity.java

> Le fichier MainActivity est disponible dans ``app/java/packageid/`` depuis android studio

```java
    // Autres imports...
    import com.gfc.apprategfc.AppRateGFC;
    
    public class MainActivity extends BridgeActivity {
      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
    
          add(AppRateGFC.class);  // Ajout du plugin AppRateGFC 
    
        }});
      }
    }
```

## 3. Screenshot

| AppRate Basic                                                        | AppRate Dark Mode                                                    | AppRate avec AppIcon                                                    |
|----------------------------------------------------------------------|----------------------------------------------------------------------|----------------------------------------------------------------------|
|![Screenshot_1](imgDoc/Screenshot_1_portrait.png?raw=true "Screenshot_1") |![Screenshot_2](imgDoc/Screenshot_2_portrait.png?raw=true "Screenshot_2") |![Screenshot_3](imgDoc/Screenshot_3_portrait.png?raw=true "Screenshot_3") |

## 4. Méthodes du plugin

| Methodes | Android |
|:-------- |:--------|
| init(options: APPRATE_OPTS): Promise<APPRATE_INIT>; | Disponible|
| checkAppStatus(options: APPRATE_OPTS_CHECK): Promise<APP_PUBLISH_STATUT>; | Disponible|
| showDirectly(): Promise<{}>; | Disponible|
| addListener(eventName: 'positifRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle; | Disponible|
| addListener(eventName: 'netralRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle;  | Disponible|
| addListener(eventName: 'negativeRateEvent', listenerFunc: (statut: APPRATE_STATUT) => void): PluginListenerHandle; | Disponible|
| addListener(eventName: 'appIsPublishEvent', listenerFunc: (statut: APP_PUBLISH_STATUT) => void): PluginListenerHandle; | Disponible|
| addListener(eventName: 'isAllReadyShow', listenerFunc: (statut: boolean) => void): PluginListenerHandle; | Disponible|

## 5. Interfaces du plugin

### 5.1 APPRATE_OPTS
> Nécessaire pour la méthode init()

| Propriété | Etat | Type | Defaut | Commentaire |
|:----------|:-----|:-----|:-------|:------------|
| appName | Obligatoire | string | null | Renseigne le nom de l'application |
| appPackageId | Obligatoire | string | null | Renseigne l'id du package (ex: com.mon.application) |
| storeUrl | Obligatoire | [MARKET_URL](#61-market_url) | Object | Renseigne le store où est hébergée l'application |
| titleAppRate | Obligatoire | string | null | Renseigne le titre (en rajoutant **%@** dans le titre il sera remplacé par __appName__) |
| messageAppRate | Obligatoire | string | null | Renseigne le message (en rajoutant **%@** dans le message, il sera remplacé par __appName__) |
| untilPrompt | Obligatoire | number | null | Renseigne le nombre d'ouverture de l'application avant affichage de la popup (conseil : 5 ouvertures) |
| daysUntilPrompt | Obligatoire | number | null | Renseigne le nombre de jour avant affichage de la popup (conseil : 3 ouvertures) |
| positifButtonStyle| Obligatoire | [BUTTON_STYLE](#53-button_style) | Object | Renseigne le texte (**Obligatoire**) et la couleur (**Optionel**) du bouton positif (ouverture du store) |
| netralButtonStyle | Obligatoire | [BUTTON_STYLE](#53-button_style) | Object | Renseigne le texte (**Obligatoire**) et la couleur (**Optionel**) du bouton neutre  (rappel) |
| negatifButtonStyle| Obligatoire | [BUTTON_STYLE](#53-button_style) | Object | Renseigne le texte (**Obligatoire**) et la couleur (**Optionel**) du bouton negatif (ne s'affichera plus jamais) |
| darkMode | Optionel | boolean | false | Reseigne l'utilisation du theme Sombre |
| addAppIcon | Optionel | boolean | false | Renseigne le souhait d'afficher l'icon de l'application dans la popup |
| backgroundColor | Optionel | string | null | Renseigne la couleur de font de la popup. Les couleurs devront commencer par # et contenir 7 caractères (ex: #4A4747) |

> Par defaut le thème sombre et le thème basic contient des couleurs. Si vous ne renseignez aucunes couleurs les boutons et le font utilisera ceux present dans lestyle de la popup. Chaque couleur peut etre changée.

### 5.2 APPRATE_OPTS_CHECK
> Nécessaire pour la méthode checkAppStatus()

| Propriété | Etat | Type | Defaut | Commentaire |
|:----------|:-----|:-----|:-------|:------------|
| appPackageId | Obligatoire | string | null | Renseigne l'id du package (ex: com.mon.application) |
| storeUrl | Obligatoire | [MARKET_URL](#61-market_url) | Object  | Renseigne le store où est hébergée l'application |

### 5.3 BUTTON_STYLE
> Nécessaire pour la méthode init()

| Propriété | Etat | Type | Defaut | Commentaire |
|:----------|:-----|:-----|:-------|:------------|
| buttonActionText  | Obligatoire | string | null | Renseigne le text du bouton |
| buttonActionColor | Optionel | string | '#E35A5A' | Les couleurs devront commencer par # et contenir 7 caractères (ex: #FFFFFF) |

### 5.4 APPRATE_INIT
> Nécessaire pour la méthode init()

| Propriété   | Type | Commentaire |
|:------------|:-----|:------------|
| appRateInit | boolean | AppRate est initialisé ou non |

### 5.5 APP_PUBLISH_STATUT
> Nécessaire pour la méthode checkAppStatus()

| Propriété   | Type | Commentaire |
|:------------|:-----|:------------|
| appOnPlay | boolean | Est present ou non dans le store |

### 5.6 APPRATE_STATUT
> Nécessaire pour les evenement __positifRateEvent__, __netralRateEvent__, __negativeRateEvent__

| Propriété | Type | Commentaire |
|:----------|:-----|:------------|
| rateStatut | [STATUT_BTN](#62-statut_btn) | Bouton cliqué par l'utilisateur |

## 6. Enums du plugin

### 6.1 MARKET_URL

| Propriété | Valeur |
|:----------|:-------|
| GOOGLE_PLAY_MARKET  | 'market://details?id=' |
| GOOGLE_PLAY_URL     | 'https://play.google.com/store/apps/details?id=' |
| APPLE_STORE_MARKET  | 'itms-apps://itunes.apple.com/app/id' |  
| APPLE_STORE_URL     | 'https://itunes.apple.com/en/lookup?bundleId=' |
| AMAZON_STORE_ANDROID_URL | 'https://www.amazon.com/gp/mas/dl/android?p=' |
| AMAZON_STORE_MARKET_ANDROID | 'amzn://apps/android?p=' |
| HUAWEI_APP_GALLERY  | 'appmarket://details?id=' |                                                         
 
### 6.2 STATUT_BTN

 | Propriété | Valeur |
 |:----------|:-------|
 | POSITIVE | 0 |
 | NETRAL | 1 |
 | NEGATIVE | 2 |

## 7. Utilisation du plugin

### 7.1 Intégration du plugin

```typescript
// Autres imports...
import{Plugins} from '@capacitor/core'
const {AppRateGFC} = Plugins;
```

### 7.2 Méthode init(options: APPRATE_OPTS)

```typescript
    const optionAppRate: APPRATE_OPTS  = {
                  appName: 'AppRateGFCDemo',
                  appPackageId: 'com.gfc.apprategfcdemo',
                  storeUrl: MARKET_URL.GOOGLE_PLAY_MARKET,
                  titleAppRate: 'Voudriez-vous noter %@ ?',
                  messageAppRate: 'Si vous aimez utiliser %@ , voulez-vous prendre un moment pour l\'évaluer? Il ne faudra pas plus d\'une minute. Merci pour votre aide!',
                  untilPrompt: 5,
                  daysUntilPrompt: 5,
                  positifButtonStyle: {
                      buttonActionText: 'Oui',
                      buttonActionColor: '#09A495',
                  },
                  netralButtonStyle: {
                      buttonActionText: 'Plus tard',
                      buttonActionColor: '#313030',
                  },
                  negatifButtonStyle: {
                      buttonActionText: 'Non',
                      buttonActionColor: '#B92F2F',
                  },
                  darkMode: false,
                  addAppIcon: false,
                  backgroundColor: '#FFFFFF'
     };

    AppRateGFC.init(optionAppRate).then((result: APPRATE_INIT) => {
                if (result.appRateInit) {
                    // Votre code...
                }
            }).catch((error) => {
                // Votre code...
            });
```

### 7.3 Méthode checkAppStatus(options: APPRATE_OPTS_CHECK)

```typescript
    const optsCheck = {
                    appPackageId: 'com.gfc.apprategfcdemo',
                    storeUrl: MARKET_URL.GOOGLE_PLAY_MARKET,
    };

    AppRateGFC.checkAppStatus(optsCheck).catch((error) => ({
        // Votre code...
    }));
```

### 7.4 Méthode showDirectly()
> Pensez a lancer la methode init() avant d'appeler showDirectly()

```typescript
   
    AppRateGFC.showDirectly().catch((error) => ({
        // Votre code...
    }));

```

### 7.5 Méthode addListener('positifRateEvent')

> Cette évenement est déclenché lorsque l'utilisateur clique sur le bouton positif
```typescript
    ngOnInit() {
        AppRateGFC.addListener('positifRateEvent', (statut: APPRATE_STATUT) => {
            // votre code...
        });
    }
```

### 7.6 Méthode addListener('netralRateEvent')

> Cette évenement est déclenché lorsque l'utilisateur clique sur le bouton neutre
```typescript
    ngOnInit() {
        AppRateGFC.addListener('netralRateEvent', (statut: APPRATE_STATUT) => {
            // votre code...
        });
    }
```

### 7.7 Méthode addListener('negativeRateEvent')

> Cette évenement est déclenché lorsque l'utilisateur clique sur le bouton négatif
```typescript
    ngOnInit() {
        AppRateGFC.addListener('negativeRateEvent', (statut: APPRATE_STATUT) => {
            // votre code...
        });
    }
```

### 7.8 Méthode addListener('appIsPublishEvent')

> Cette évenement est déclenché lorsque l'on utilise la methode checkAppStatus(options: APPRATE_OPTS_CHECK)
```typescript
    ngOnInit() {
        AppRateGFC.addListener('appIsPublishEvent', (statut: APP_PUBLISH_STATUT) => {
            if(status.appOnPlay)
            {
                // votre code...
            }           
        });
    }
```

### 7.9 Méthode addListener('isAllReadyShow')

> Cette évenement est déclenché si l'utilisateur a déjà reçu la demande et s'il a cliqué le bouton positif ou negatif
```typescript
    ngOnInit() {
        AppRateGFC.addListener('isAllReadyShow', (statut: boolean) => {
            // votre code...
        });
    }
```

## 8. Ajouter une icone dans la popup

> Si vous souhaitez ajouter l'icone de votre application dans la popup il vous suffit de faire un clic droit sur le repertoire **res** dans __capacitor-apprate-gfc/android/src/main/res__
> Selectionner New -> Image Asset et ajouter l'icone de votre application.

## 9. Demo du plugin

> Téléchargez notre [demo](https://github.com/SASGeniusFlashConception/capacitor-apprate-gfc/tree/master/demo/AppRateGFCDemo).

## 10. Nos autres plugins

| Nom | Package | Version | Plateformes |
|:----|:--------|:--------|:------------|
| SnackBarGFC | [capacitor-apprate-gfc](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc) | [![Version Npm](https://img.shields.io/npm/v/capacitor-snackbar-gfc)](https://www.npmjs.com/package/capacitor-snackbar-gfc) | Android |
| DateTimePickerGFC | [capacitor-datetimepicker-gfc](https://github.com/SASGeniusFlashConception/capacitor-datetimepicker-gfc) | [![Version Npm](https://img.shields.io/npm/v/capacitor-datetimepicker-gfc)](https://www.npmjs.com/package/capacitor-datetimepicker-gfc) | Android |
| CalendarEventGFC | [capacitor-calendarevent-gfc](https://github.com/SASGeniusFlashConception/capacitor-calendarevent-gfc) | [![Version Npm](https://img.shields.io/npm/v/capacitor-calendarevent-gfc)](https://www.npmjs.com/package/capacitor-calendarevent-gfc) | Android |
