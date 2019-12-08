import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Module } from '../model/Module';

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  // BASE_PATH: 'http://localhost:8080';

  constructor(private http: HttpClient) { 

  }

  retrieveAllModules(name) {
    return this.http.get<[]>(`http://localhost:8080/instructors/${name}/modules`);
  }

  // eslint-disable-next-line
  retrieveModule(name, id) {
    //console.log('executed service')
    return this.http.get<Module>(`http://localhost:8080/instructors/${name}/modules/${id}`);
  }

  // eslint-disable-next-line
  deleteModule(name, id) {
    //console.log('executed service')
    return this.http.delete<Object>(`http://localhost:8080/instructors/${name}/modules/${id}`);
  }

  // eslint-disable-next-line
  updateModule(name, id, module) {
    //console.log('executed service')
    return this.http.put<Object>(`http://localhost:8080/instructors/${name}/modules/${id}`, module);
  }

  // eslint-disable-next-line
  createModule(name, module) {
    //console.log('executed service')
    return this.http.post<Object>(`http://localhost:8080/instructors/${name}/modules`, module);
  }

}
