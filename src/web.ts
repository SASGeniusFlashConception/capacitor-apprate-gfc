import { WebPlugin } from '@capacitor/core';
import { AppRateGFCPlugin } from './definitions';

export class AppRateGFCWeb extends WebPlugin implements AppRateGFCPlugin {
  constructor() {
    super({
      name: 'AppRateGFC',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const AppRateGFC = new AppRateGFCWeb();

export { AppRateGFC };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(AppRateGFC);
