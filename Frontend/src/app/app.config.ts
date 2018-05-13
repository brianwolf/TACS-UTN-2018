import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AppConfig {

  private config;
  private environment;

  constructor(private http: HttpClient) { }

  /**
   * Use to get the data found in the second file (config file)
   */
  public getConfig(key) {
    return this.config[key];
  }

  /**
   * Use to get the data found in the first file (env file)
   */
  public getEnv(key) {
    return this.environment[key];
  }

  /**
   * This method:
   *   a) Loads "env.json" to get the current working environment (e.g.: 'production', 'development')
   *   b) Loads "config.[env].json" to get all env's variables (e.g.: 'config.development.json')
   */
  public load() {
    return new Promise((resolve, reject) => {
      this.http.get('./assets/configuration/environment.json').subscribe((environment: any) => {
        console.log('ENVIRONMENT: ' + environment.env);
        this.environment = environment;

        this.http.get(`./assets/configuration/config.${environment.env}.json`).subscribe((config: any) => {
          console.log('HOST: ' + config.host);
          this.config = config;
          resolve(true);
        });

      });
    });
  }

}
