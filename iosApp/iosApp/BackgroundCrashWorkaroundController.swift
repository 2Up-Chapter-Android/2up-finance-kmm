////
////  BackgroundCrashWorkaroundController.swift
////  iosApp
////
////  Created by Duc Tam on 15/06/2023.
////  Copyright © 2023 orgName. All rights reserved.
////
//
//import Foundation
//import SwiftUI
//import shared
//
//class BackgroundCrashWorkaroundController: UIViewController {
//    
//////    let viewModel: ApplicationViewModel
////    let composeController: UIViewController
////    
////    init(viewModel: ApplicationViewModel) {
//////        self.viewModel = viewModel
////        
////        composeController = ComposeRootControllerKt.getRootController(viewModel: viewModel)
////        
////        super.init(nibName: nil, bundle: nil)
////    }
////    
////    required init?(coder: NSCoder) {
////        fatalError("init(coder:) has not been implemented")
////    }
////    
////    override func viewWillAppear(_ animated: Bool) {
////        super.viewWillAppear(animated)
////        
////        if composeController.parent == nil {
////            addChild(composeController)
////            composeController.view.frame = view.bounds
////            view.addSubview(composeController.view)
////            composeController.didMove(toParent: self)
////        }
////    }
//}
