# SnackBarGFC - Android

[![Version Npm](https://img.shields.io/npm/v/capacitor-snackbar-gfc)](https://www.npmjs.com/package/capacitor-snackbar-gfc)

> Le plugin **capacitor-snackbar-gfc** est une implementation native du composant SnackBars d'Android.
> Vous pouvez maintenant utiliser ce package comme plugin [Ionic Capacitor](https://capacitor.ionicframework.com) dans votre application.

## Soutenez nos développements
> Votre don nous permettra de developper plus de plugin open source mais également a maintenir ceux déjà publiés pour garantir une compatibilité avec les future version d'android, d'ios et de capacitor.
> Merci de votre soutien ! 

❤[Je fais un don](paypal.me/GFCPAYPAL)❤

## Sommaire

1. [Plateformes prise en charge](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#1-plateformes-prise-en-charge) 
2. [Installation](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#2-installation)  
    2.1 [Récuperation du package sur npm](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#r%C3%A9cuperation-du-package-sur-npm)  
    2.2 [Ajout du plugin dans votre MainActivity.java](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#ajout-du-plugin-dans-votre-mainactivityjava)   
3. [Screenshot](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#3-screenshot) 
4. [Méthodes du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#4-m%C3%A9thodes-du-plugin) 
5. [Interfaces du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#5-interfaces-du-plugin)  
    5.1 [SnackbarOpts](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#snackbaropts)   
6. [Enums du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#6-enums-du-plugin)   
    6.1 [DURATION_ENUM](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#duration_enum)   
    6.2 [POSITION_ENUM](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#position_enum)  
7. [Utilisation du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#7-utilisation-du-plugin)  
    7.1 [Intégration du plugin](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#int%C3%A9gration-du-plugin)  
    7.2 [Méthode show(options:SnackbarOpts)](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#m%C3%A9thode-showoptions-snackbaropts)  
    7.3 [Méthode addListener('snackbarEvent')](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#m%C3%A9thode-addlistenersnackbarevent)  
8. [Demo](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#8-demo-du-plugin) 
9. [Nos autres plugins](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/blob/master/doc/README-FR.md#9-nos-autres-plugins) 


## 1. Plateformes prise en charge
- [x] Android

## 2. Installation
### 2.1 Récuperation du package sur npm

``` npm install capacitor-snackbar-gfc --save ```

### 2.2 Ajout du plugin dans votre MainActivity.java
> Le fichier MainActivity est disponible dans ``app/java/packageid/`` depusi android studio

```java
    // Autres imports...
    import com.gfc.snackbargfc.SnackBarGFC;
    
    public class MainActivity extends BridgeActivity {
      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
    
          add(SnackBarGFC.class);  // Ajout du plugin SnackBarGFC 
    
        }});
      }
    }
```

## 3. Screenshot

## 4. Méthodes du plugin

| Methodes                                                                                | Android   |
|:----------------------------------------------------------------------------------------|:----------|
| show(option: SnackbarOpts): Promise<{}>                                                 | Disponible|
| addListener(eventName: 'snackbarEvent', listenerFunc: () => void): PluginListenerHandle | Disponible|

## 5. Interfaces du plugin
### 5.1 SnackbarOpts
| Propriété      | Etat       | Type         | Defaut   | Commentaire                                                 |
|:---------------|:-----------|:-------------|:---------|:------------------------------------------------------------|
| text           | Obligatoire| string       | null     |                                                             |
| btnText        | Obligatoire| string       | null     |                                                             |
| duration       | Obligatoire| DURATION_ENUM| 'short'  |                                                             |
| positionText   | Optionel   | POSITION_ENUM| null     |                                                             |
| backgroundColor| Optionel   | string       | '#4A4747'| Les coleurs devront commencer par # et contenir 6 caractères|
| buttonTextColor| Optionel   | string       | '#E35A5A'| Les coleurs devront commencer par # et contenir 6 caractères|
| messageColor   | Optionel   | string       | '#FFFFFF'| Les coleurs devront commencer par # et contenir 6 caractères|

## 6. Enums du plugin
### 6.1 DURATION_ENUM
| Propriété | Valeur      |
|:----------|:------------|
| SHORT     | 'short'     |
| LONG      | 'long'      |
| INDEFINITE| 'indefinite'|                                                           
 
### 6.2 POSITION_ENUM
 | Propriété| Valeur   |
 |:---------|:---------|
 | DEFAULT  | 'default'|
 | CENTER   | 'center' |

## 7. Utilisation du plugin
### 7.1 Intégration du plugin
```typescript
// Autres imports...
import{Plugins} from '@capacitor/core'
const {SnackBarGFC} = Plugins;
```
### 7.2 Méthode show(options: SnackbarOpts)
```typescript
const opts: SnackbarOpts = {
    text: 'Votre message...',
    btnText: 'Texte du bouton',
    duration: DURATION_ENUM.SHOT,
    positionText: POSITION_ENUM.DEFAULT,
    backgroundColor: '#4A4747',
    buttonTextColor: '#E35A5A',
    messageColor: '#FFFFFF'
}

SnackBarGFC.show(opts);
```
### 7.3 Méthode addListener('snackbarEvent')
> Cette évenement est déclenché lorsque l'utilisateur clique sur le bouton du composant
```typescript
    ngOnInit() {
        SnackBarGFC.addListener('snackbarEvent', () => {
            // votre code...
        });
    }
```

## 8. Demo du plugin

> Téléchargez notre [demo](https://github.com/SASGeniusFlashConception/capacitor-snackbar-gfc/tree/master/demo/SnackBarGFCDemo). Elle contient deux exemples: une snackbar light et une snackbar dark.

## 9. Nos autres plugins

| Nom              | Package                          | Version | Plateformes |
|:-----------------|:---------------------------------|:--------|:------------|
| AppRateGFC | [capacitor-app-rate-gfc](https://github.com/SASGeniusFlashConception/capacitor-app-rate-gfc) | 1.0.0 | Android |
| DateTimePickerGFC | [capacitor-datetimepicker-gfc](https://github.com/SASGeniusFlashConception/capacitor-datetimepicker-gfc) | 1.0.0 | Android |
| CalendarEventGFC | [capacitor-calendarevent-gfc](https://github.com/SASGeniusFlashConception/capacitor-calendarevent-gfc) | 1.0.0 | Android |
