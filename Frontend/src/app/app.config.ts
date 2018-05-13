import { Inject, Injectable } from '@angular/core';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class AppConfig {

    private config: Object = null;
    private env:    Object = null;

    constructor(private http: HttpClient) {

    }

    /**
     * Use to get the data found in the second file (config file)
     */
    public getConfig(key: any) {
        return this.config[key];
    }

    /**
     * Use to get the data found in the first file (env file)
     */
    public getEnv(key: any) {
        return this.env[key];
    }

    /**
     * This method:
     *   a) Loads "env.json" to get the current working environment (e.g.: 'production', 'development')
     *   b) Loads "config.[env].json" to get all env's variables (e.g.: 'config.development.json')
     */
    public load() {
        return new Promise((resolve, reject) => {
            this.http.get('./assets/configuration/env.json').subscribe((envResponse: any) => {
              console.log("ENVIRONMENT: "+envResponse);
                this.env = envResponse;

                 this.http.get('./assets/configuration/config.' + envResponse.env + '.json').subscribe((responseData: any) => 
                          {
                            console.log("CONFIG: "+responseData);
                            this.config = responseData;
                            resolve(true);
                          });

        });
    });
  }
}