using System;
using Microsoft.Xna.Framework;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewModdingAPI.Utilities;
using StardewValley;

namespace BlayKeyBlocksMod
{
    /// <summary>The mod entry point.</summary>
    public class ModEntry : Mod, IAssetLoader
    {
        /*********
        ** Public methods
        *********/
        public bool CanLoad<T>(IAssetInfo asset)
        {
            //Abigail texture changes
            if (asset.AssetNameEquals("Portraits/Abigail"))
            {
                return true;
            }
            if (asset.AssetNameEquals("Portraits/Abigail_Beach"))
            {
                return true;
            }
            if (asset.AssetNameEquals("Characters/Abigail"))
            {
                return true;
            }
            if (asset.AssetNameEquals("Characters/Abigail_Beach"))
            {
                return true;
            }
            //Dog texture changes
            if (asset.AssetNameEquals("Animals/dog1"))
            {
                return true;
            }
            if (asset.AssetNameEquals("LooseSprites/Cursors"))
            {
                return true;
            }
            //Dialogue changes
            if (asset.AssetNameEquals("Characters/Dialogue/MarriageDialogue"))
            {
                return true;
            }
            if (asset.AssetNameEquals("Characters/Dialogue/MarriageDialogueAbigail"))
            {
                return true;
            }

            return false;
        }

        public T Load<T>(IAssetInfo asset)
        {
            //Abigail texture changes
            if (asset.AssetNameEquals("Portraits/Abigail"))
            {
                return this.Helper.Content.Load<T>("assets/abigail-portraits.png", ContentSource.ModFolder);
            }
            if (asset.AssetNameEquals("Portraits/Abigail_Beach"))
            {
                return this.Helper.Content.Load<T>("assets/Abigail_Beach_Portraits", ContentSource.ModFolder);
            }
            if (asset.AssetNameEquals("Characters/Abigail"))
            {
                return this.Helper.Content.Load<T>("assets/Abigail.png");
            }
            if (asset.AssetNameEquals("Characters/Abigail_Beach"))
            {
                return this.Helper.Content.Load<T>("assets/Abigail_Beach.png");
            }
            //Dog texture changes
            if (asset.AssetNameEquals("Animals/dog1"))
            {
                return this.Helper.Content.Load<T>("assets/dog1.png", ContentSource.ModFolder);
            }
            if (asset.AssetNameEquals("LooseSprites/Cursors"))
            {
                return this.Helper.Content.Load<T>("assets/Cursors.png", ContentSource.ModFolder);
            }
            //Dialogue changes
            if (asset.AssetNameEquals("Characters/Dialogue/MarriageDialogue"))
            {
                return this.Helper.Content.Load<T>("assets/MarriageDialogue.xnb", ContentSource.ModFolder);
            }
            if (asset.AssetNameEquals("Characters/Dialogue/MarriageDialogueAbigail"))
            {
                return this.Helper.Content.Load<T>("assets/MarriageDialogueAbigail.xnb");
            }

            throw new InvalidOperationException($"Unexpected asset. You done messed up!");
        }

        /// <summary>The mod entry point, called after the mod is first loaded.</summary>
        /// <param name="helper">Provides simplified APIs for writing mods.</param>
        public override void Entry(IModHelper helper)
        {
            helper.Events.Input.ButtonPressed += this.OnButtonPressed;
        }


        /*********
        ** Private methods
        *********/
        /// <summary>Raised after the player presses a button on the keyboard, controller, or mouse.</summary>
        /// <param name="sender">The event sender.</param>
        /// <param name="e">The event data.</param>
        private void OnButtonPressed(object sender, ButtonPressedEventArgs e)
        {
            // ignore if player hasn't loaded a save yet
            if (!Context.IsWorldReady)
                return;

            // print button presses to the console window
            this.Monitor.Log($"{Game1.player.Name} pressed {e.Button}.", LogLevel.Debug);
        }
    }
}