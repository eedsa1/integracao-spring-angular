import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { ModuleService } from '../service/module.service';
import { Module } from '../model/Module';

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.css']
})
export class ModuleComponent implements OnInit {

  id: number;
  errors = [];
  //name = 'in28minutes';
  module: Module;

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private authenticationService: AuthenticationService,
    private moduleService: ModuleService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.module = new Module(this.id, '', '');
    if(this.id != -1) {
      this.moduleService.retrieveModule(this.authenticationService.username, this.id).subscribe((module) => {
        this.module = module;
      });
    }
  }

  saveSourse() {
    if(this.id != -1) {
      this.moduleService.createModule(this.authenticationService.username, this.module).subscribe(() => {
        this.router.navigate(['modules']);
      })
    } else {
      this.moduleService.updateModule(this.authenticationService.username, this.id, this.module).subscribe(() => {
        this.router.navigate(['modules']);
      })
    }
  }

}
