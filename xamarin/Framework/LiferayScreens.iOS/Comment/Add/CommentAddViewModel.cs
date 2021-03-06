﻿using Foundation;
using ObjCRuntime;

namespace LiferayScreens
{
    interface ICommentAddViewModel {}

    // @protocol CommentAddViewModel
    [Protocol]
    interface CommentAddViewModel
    {
        // @required @property (copy, nonatomic) NSString * _Nonnull body;
        [Abstract]
        [Export("body")]
        string Body { get; set; }
    }
}
