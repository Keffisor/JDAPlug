package com.Keffisor21.JDAExpansion.EventController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import com.Keffisor21.JDAExpansion.API.MessageConsoleReceivedEvent;

import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandCreateEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandDeleteEvent;
import net.dv8tion.jda.api.events.application.ApplicationCommandUpdateEvent;
import net.dv8tion.jda.api.events.application.GenericApplicationCommandEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.GenericStoreChannelEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNewsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateRegionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateAvatarEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBannerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateLocaleEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxMembersEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxPresencesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNSFWLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceRequestToSpeakEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceVideoEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateIconEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.stage.GenericStageInstanceEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceCreateEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceDeleteEvent;
import net.dv8tion.jda.api.events.stage.update.GenericStageInstanceUpdateEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdatePrivacyLevelEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdateTopicEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventsRegistration extends ListenerAdapter {
	public static HashMap<Class<?>, List<EventValues>> registered = new HashMap<>();
	
	public static void loadEvent(PluginListener o) {
		for(Method method : o.getClass().getDeclaredMethods()) {
			EventHandler e = method.getAnnotation(EventHandler.class);
			if(e == null || method.getParameters().length != 1) continue;
			Class<?> type = method.getParameters()[0].getType();
			setEventType(type, new EventValues(o, type, method, e));
		}
	}
	
	public static void setEventType(Class<?> o, EventValues values) {
		new EventsRegistration().getListEvents().forEach(cl -> {
			try {
				if(o.getName().equalsIgnoreCase(cl.getName())) {
					if(registered.get(cl) == null) registered.put(cl, new ArrayList<>());
					List<EventValues> list = registered.get(cl);
					if(list.stream().anyMatch(value -> value.getClass().getName().equals(values.getObject().getClass().getName()))) return;
					list.add(values);
					registered.put(cl, list);
				}
			} catch (ClassCastException e) {
				// TODO: handle exception
			}
		});	
	}
	
	public List<EventValues> getClass(Class<?> o) {
		if(registered.get(o) == null) return new ArrayList<EventValues>();
		return registered.get(o);
	}
	
	public List<Class<?>> getListEvents() {
		List<Class<?>> cls = new ArrayList<>();
		for(Method method : ListenerAdapter.class.getDeclaredMethods()) {
			if(method.getParameters().length != 1) continue;
			Nonnull nonnull = method.getParameters()[0].getAnnotation(Nonnull.class);
			if(nonnull == null) continue;
			cls.add(method.getParameters()[0].getType());
		}
		cls.add(MessageConsoleReceivedEvent.class);
		return cls;
	}
	
	public void executeClass(Class<?> cl, Object e) {
		orderPriority(getClass(cl)).forEach(value -> {
			try {
				try {
				value.getMethod().invoke(value.getObject(), e);
				} catch(IllegalArgumentException ex) {}
			} catch (Exception ex) {
			}
		});
	}
	
	public List<EventValues> orderPriority(List<EventValues> values) {
		LinkedList<EventValues> result = new LinkedList<>();
		for(EventPriority priority : EventPriority.values()) {
			values.stream().filter(v -> v.getAnnotation().priority().equals(priority)).forEach(result::add);
		}
		Collections.reverse(result);
		return result;
	}
	
	//jda events ListenerAdapter.class
    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.0")
    @ReplaceWith("onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    @Override public void onTextChannelUpdatePermissions(@Nonnull TextChannelUpdatePermissionsEvent event) {executeClass(TextChannelUpdatePermissionsEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.0")
    @ReplaceWith("onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    @Override public void onStoreChannelUpdatePermissions(@Nonnull StoreChannelUpdatePermissionsEvent event) {executeClass(StoreChannelUpdatePermissionsEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.0")
    @ReplaceWith("onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    @Override public void onVoiceChannelUpdatePermissions(@Nonnull VoiceChannelUpdatePermissionsEvent event) {executeClass(VoiceChannelUpdatePermissionsEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.0")
    @ReplaceWith("onPermissionOverrideUpdate(), onPermissionOverrideCreate(), and onPermissionOverrideDelete()")
    @Override public void onCategoryUpdatePermissions(@Nonnull CategoryUpdatePermissionsEvent event) {executeClass(CategoryUpdatePermissionsEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.0")
    @ReplaceWith("onGuildMemberRemove(GuildMemberRemoveEvent)")
    @Override public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event) {executeClass(GuildMemberLeaveEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.1")
    @ReplaceWith("onResumed(ResumedEvent)")
    @Override public void onResume(@Nonnull ResumedEvent event) {executeClass(ResumedEvent.class, event);}

    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.2.1")
    @ReplaceWith("onReconnected(ReconnectedEvent)")
    @Override public void onReconnect(@Nonnull ReconnectedEvent event) {executeClass(ReconnectedEvent.class, event);}

    @Override public void onGenericEvent(@Nonnull GenericEvent event) {executeClass(GenericEvent.class, event);}
    @Override public void onGenericUpdate(@Nonnull UpdateEvent<?, ?> event) {executeClass(UpdateEvent.class, event);}
    @Override public void onRawGateway(@Nonnull RawGatewayEvent event) {executeClass(RawGatewayEvent.class, event);}
    @Override public void onGatewayPing(@Nonnull GatewayPingEvent event) {executeClass(GatewayPingEvent.class, event);}

    //JDA Events
    @Override public void onReady(@Nonnull ReadyEvent event) {executeClass(ReadyEvent.class, event);}
    @Override public void onResumed(@Nonnull ResumedEvent event) {executeClass(ResumedEvent.class, event);}
    @Override public void onReconnected(@Nonnull ReconnectedEvent event) {executeClass(ReconnectedEvent.class, event);}
    @Override public void onDisconnect(@Nonnull DisconnectEvent event) {executeClass(DisconnectEvent.class, event);}
    @Override public void onShutdown(@Nonnull ShutdownEvent event) {executeClass(ShutdownEvent.class, event);}
    @Override public void onStatusChange(@Nonnull StatusChangeEvent event) {executeClass(StatusChangeEvent.class, event);}
    @Override public void onException(@Nonnull ExceptionEvent event) {executeClass(ExceptionEvent.class, event);}

    //Interaction Events
    @Override public void onSlashCommand(@Nonnull SlashCommandEvent event) {executeClass(SlashCommandEvent.class, event);}
    @Override public void onButtonClick(@Nonnull ButtonClickEvent event) {executeClass(ButtonClickEvent.class, event);}
    @Override public void onSelectionMenu(@Nonnull SelectionMenuEvent event) {executeClass(SelectionMenuEvent.class, event);}

    //Application Events
    @Override public void onApplicationCommandUpdate(@Nonnull ApplicationCommandUpdateEvent event) {executeClass(ApplicationCommandUpdateEvent.class, event);}
    @Override public void onApplicationCommandDelete(@Nonnull ApplicationCommandDeleteEvent event) {executeClass(ApplicationCommandDeleteEvent.class, event);}
    @Override public void onApplicationCommandCreate(@Nonnull ApplicationCommandCreateEvent event) {executeClass(ApplicationCommandCreateEvent.class, event);}

    //User Events
    @Override public void onUserUpdateName(@Nonnull UserUpdateNameEvent event) {executeClass(UserUpdateNameEvent.class, event);}
    @Override public void onUserUpdateDiscriminator(@Nonnull UserUpdateDiscriminatorEvent event) {executeClass(UserUpdateDiscriminatorEvent.class, event);}
    @Override public void onUserUpdateAvatar(@Nonnull UserUpdateAvatarEvent event) {executeClass(UserUpdateAvatarEvent.class, event);}
    @Override public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {executeClass(UserUpdateOnlineStatusEvent.class, event);}
    @Override public void onUserUpdateActivityOrder(@Nonnull UserUpdateActivityOrderEvent event) {executeClass(UserUpdateActivityOrderEvent.class, event);}
    @Override public void onUserUpdateFlags(@Nonnull UserUpdateFlagsEvent event) {executeClass(UserUpdateFlagsEvent.class, event);}
    @Override public void onUserTyping(@Nonnull UserTypingEvent event) {executeClass(UserTypingEvent.class, event);}
    @Override public void onUserActivityStart(@Nonnull UserActivityStartEvent event) {executeClass(UserActivityStartEvent.class, event);}
    @Override public void onUserActivityEnd(@Nonnull UserActivityEndEvent event) {executeClass(UserActivityEndEvent.class, event);}
    @Override public void onUserUpdateActivities(@Nonnull UserUpdateActivitiesEvent event) {executeClass(UserUpdateActivitiesEvent.class, event);}

    //Self Events. Fires only in relation to the currently logged in account.
    @Override public void onSelfUpdateAvatar(@Nonnull SelfUpdateAvatarEvent event) {executeClass(SelfUpdateAvatarEvent.class, event);}
    @Override public void onSelfUpdateMFA(@Nonnull SelfUpdateMFAEvent event) {executeClass(SelfUpdateMFAEvent.class, event);}
    @Override public void onSelfUpdateName(@Nonnull SelfUpdateNameEvent event) {executeClass(SelfUpdateNameEvent.class, event);}
    @Override public void onSelfUpdateVerified(@Nonnull SelfUpdateVerifiedEvent event) {executeClass(SelfUpdateVerifiedEvent.class, event);}

    //Message Events
    //Guild (TextChannel) Message Events
    @Override public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {executeClass(GuildMessageReceivedEvent.class, event);}
    @Override public void onGuildMessageUpdate(@Nonnull GuildMessageUpdateEvent event) {executeClass(GuildMessageUpdateEvent.class, event);}
    @Override public void onGuildMessageDelete(@Nonnull GuildMessageDeleteEvent event) {executeClass(GuildMessageDeleteEvent.class, event);}
    @Override public void onGuildMessageEmbed(@Nonnull GuildMessageEmbedEvent event) {executeClass(GuildMessageEmbedEvent.class, event);}
    @Override public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {executeClass(GuildMessageReactionAddEvent.class, event);}
    @Override public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {executeClass(GuildMessageReactionRemoveEvent.class, event);}
    @Override public void onGuildMessageReactionRemoveAll(@Nonnull GuildMessageReactionRemoveAllEvent event) {executeClass(GuildMessageReactionRemoveAllEvent.class, event);}
    @Override public void onGuildMessageReactionRemoveEmote(@Nonnull GuildMessageReactionRemoveEmoteEvent event) {executeClass(GuildMessageReactionRemoveEmoteEvent.class, event);}

    //Private Message Events
    @Override public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event) {executeClass(PrivateMessageReceivedEvent.class, event);}
    @Override public void onPrivateMessageUpdate(@Nonnull PrivateMessageUpdateEvent event) {executeClass(PrivateMessageUpdateEvent.class, event);}
    @Override public void onPrivateMessageDelete(@Nonnull PrivateMessageDeleteEvent event) {executeClass(PrivateMessageDeleteEvent.class, event); }
    @Override public void onPrivateMessageEmbed(@Nonnull PrivateMessageEmbedEvent event) {executeClass(PrivateMessageEmbedEvent.class, event);}
    @Override public void onPrivateMessageReactionAdd(@Nonnull PrivateMessageReactionAddEvent event) {executeClass(PrivateMessageReactionAddEvent.class, event);}
    @Override public void onPrivateMessageReactionRemove(@Nonnull PrivateMessageReactionRemoveEvent event) {executeClass(PrivateMessageReactionRemoveEvent.class, event);}

    //Combined Message Events (Combines Guild and Private message into 1 event)
    @Override public void onMessageReceived(@Nonnull MessageReceivedEvent event) {executeClass(MessageReceivedEvent.class, event);}
    @Override public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {executeClass(MessageUpdateEvent.class, event);}
    @Override public void onMessageDelete(@Nonnull MessageDeleteEvent event) {executeClass(MessageDeleteEvent.class, event);}
    @Override public void onMessageBulkDelete(@Nonnull MessageBulkDeleteEvent event) {executeClass(MessageBulkDeleteEvent.class, event);}
    @Override public void onMessageEmbed(@Nonnull MessageEmbedEvent event) {executeClass(MessageEmbedEvent.class, event);}
    @Override public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {executeClass(MessageReactionAddEvent.class, event);}
    @Override public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {executeClass(MessageReactionRemoveEvent.class, event);}
    @Override public void onMessageReactionRemoveAll(@Nonnull MessageReactionRemoveAllEvent event) {executeClass(MessageReactionRemoveAllEvent.class, event);}
    @Override public void onMessageReactionRemoveEmote(@Nonnull MessageReactionRemoveEmoteEvent event) {executeClass(MessageReactionRemoveEmoteEvent.class, event);}

    //PermissionOverride Events
    @Override public void onPermissionOverrideDelete(@Nonnull PermissionOverrideDeleteEvent event) {executeClass(PermissionOverrideDeleteEvent.class, event);}
    @Override public void onPermissionOverrideUpdate(@Nonnull PermissionOverrideUpdateEvent event) {executeClass(PermissionOverrideUpdateEvent.class, event);}
    @Override public void onPermissionOverrideCreate(@Nonnull PermissionOverrideCreateEvent event) {executeClass(PermissionOverrideCreateEvent.class, event);}

    //StoreChannel Events
    @Override public void onStoreChannelDelete(@Nonnull StoreChannelDeleteEvent event) {executeClass(StoreChannelDeleteEvent.class, event);}
    @Override public void onStoreChannelUpdateName(@Nonnull StoreChannelUpdateNameEvent event) {executeClass(StoreChannelUpdateNameEvent.class, event);}
    @Override public void onStoreChannelUpdatePosition(@Nonnull StoreChannelUpdatePositionEvent event) {executeClass(StoreChannelUpdatePositionEvent.class, event);}
    @Override public void onStoreChannelCreate(@Nonnull StoreChannelCreateEvent event) {executeClass(StoreChannelCreateEvent.class, event);}

    //TextChannel Events
    @Override public void onTextChannelDelete(@Nonnull TextChannelDeleteEvent event) {executeClass(TextChannelDeleteEvent.class, event);}
    @Override public void onTextChannelUpdateName(@Nonnull TextChannelUpdateNameEvent event) {executeClass(TextChannelUpdateNameEvent.class, event);}
    @Override public void onTextChannelUpdateTopic(@Nonnull TextChannelUpdateTopicEvent event) {executeClass(TextChannelUpdateTopicEvent.class, event);}
    @Override public void onTextChannelUpdatePosition(@Nonnull TextChannelUpdatePositionEvent event) {executeClass(TextChannelUpdatePositionEvent.class, event);}
    @Override public void onTextChannelUpdateNSFW(@Nonnull TextChannelUpdateNSFWEvent event) {executeClass(TextChannelUpdateNSFWEvent.class, event);}
    @Override public void onTextChannelUpdateParent(@Nonnull TextChannelUpdateParentEvent event) {executeClass(TextChannelUpdateParentEvent.class, event);}
    @Override public void onTextChannelUpdateSlowmode(@Nonnull TextChannelUpdateSlowmodeEvent event) {executeClass(TextChannelUpdateSlowmodeEvent.class, event);}
    @Override public void onTextChannelUpdateNews(@Nonnull TextChannelUpdateNewsEvent event) {executeClass(TextChannelUpdateNewsEvent.class, event);}
    @Override public void onTextChannelCreate(@Nonnull TextChannelCreateEvent event) {executeClass(TextChannelCreateEvent.class, event);}

    //VoiceChannel Events
    @Override public void onVoiceChannelDelete(@Nonnull VoiceChannelDeleteEvent event) {executeClass(VoiceChannelDeleteEvent.class, event);}
    @Override public void onVoiceChannelUpdateName(@Nonnull VoiceChannelUpdateNameEvent event) {executeClass(VoiceChannelUpdateNameEvent.class, event);}
    @Override public void onVoiceChannelUpdatePosition(@Nonnull VoiceChannelUpdatePositionEvent event) {executeClass(VoiceChannelUpdatePositionEvent.class, event);}
    @Override public void onVoiceChannelUpdateUserLimit(@Nonnull VoiceChannelUpdateUserLimitEvent event) {executeClass(VoiceChannelUpdateUserLimitEvent.class, event);}
    @Override public void onVoiceChannelUpdateBitrate(@Nonnull VoiceChannelUpdateBitrateEvent event) {executeClass(VoiceChannelUpdateBitrateEvent.class, event);}
    @Override public void onVoiceChannelUpdateParent(@Nonnull VoiceChannelUpdateParentEvent event) {executeClass(VoiceChannelUpdateParentEvent.class, event);}
    @Override public void onVoiceChannelUpdateRegion(@Nonnull VoiceChannelUpdateRegionEvent event) {executeClass(VoiceChannelUpdateRegionEvent.class, event);}
    @Override public void onVoiceChannelCreate(@Nonnull VoiceChannelCreateEvent event) {executeClass(VoiceChannelCreateEvent.class, event);}

    //Category Events
    @Override public void onCategoryDelete(@Nonnull CategoryDeleteEvent event) {executeClass(CategoryDeleteEvent.class, event);}
    @Override public void onCategoryUpdateName(@Nonnull CategoryUpdateNameEvent event) {executeClass(CategoryUpdateNameEvent.class, event);}
    @Override public void onCategoryUpdatePosition(@Nonnull CategoryUpdatePositionEvent event) {executeClass(CategoryUpdatePositionEvent.class, event);}
    @Override public void onCategoryCreate(@Nonnull CategoryCreateEvent event) {executeClass(CategoryCreateEvent.class, event);}

    //PrivateChannel Events

    /**
     * @deprecated This event is no longer supported by discord
     */
    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.3.0")
    @Override public void onPrivateChannelCreate(@Nonnull PrivateChannelCreateEvent event) {executeClass(PrivateChannelCreateEvent.class, event);}
    @Deprecated
    @ForRemoval(deadline = "4.4.0")
    @DeprecatedSince("4.3.0")
    @Override public void onPrivateChannelDelete(@Nonnull PrivateChannelDeleteEvent event) {executeClass(PrivateChannelDeleteEvent.class, event);}

    //StageInstance Event
    @Override public void onStageInstanceDelete(@Nonnull StageInstanceDeleteEvent event) {executeClass(StageInstanceDeleteEvent.class, event);}
    @Override public void onStageInstanceUpdateTopic(@Nonnull StageInstanceUpdateTopicEvent event) {executeClass(StageInstanceUpdateTopicEvent.class, event);}
    @Override public void onStageInstanceUpdatePrivacyLevel(@Nonnull StageInstanceUpdatePrivacyLevelEvent event) {executeClass(StageInstanceUpdatePrivacyLevelEvent.class, event);}
    @Override public void onStageInstanceCreate(@Nonnull StageInstanceCreateEvent event) {executeClass(StageInstanceCreateEvent.class, event);}

    //Guild Events
    @Override public void onGuildReady(@Nonnull GuildReadyEvent event) {executeClass(GuildReadyEvent.class, event);}
    @Override public void onGuildTimeout(@Nonnull GuildTimeoutEvent event) {executeClass(GuildTimeoutEvent.class, event);}
    @Override public void onGuildJoin(@Nonnull GuildJoinEvent event) {executeClass(GuildJoinEvent.class, event);}
    @Override public void onGuildLeave(@Nonnull GuildLeaveEvent event) {executeClass(GuildLeaveEvent.class, event);}
    @Override public void onGuildAvailable(@Nonnull GuildAvailableEvent event) {executeClass(GuildAvailableEvent.class, event);}
    @Override public void onGuildUnavailable(@Nonnull GuildUnavailableEvent event) {executeClass(GuildUnavailableEvent.class, event);}
    @Override public void onUnavailableGuildJoined(@Nonnull UnavailableGuildJoinedEvent event) {executeClass(UnavailableGuildJoinedEvent.class, event);}
    @Override public void onUnavailableGuildLeave(@Nonnull UnavailableGuildLeaveEvent event) {executeClass(UnavailableGuildLeaveEvent.class, event);}
    @Override public void onGuildBan(@Nonnull GuildBanEvent event) {executeClass(GuildBanEvent.class, event);}
    @Override public void onGuildUnban(@Nonnull GuildUnbanEvent event) {executeClass(GuildUnbanEvent.class, event);}
    @Override public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {executeClass(GuildMemberRemoveEvent.class, event);}

    //Guild Update Events
    @Override public void onGuildUpdateAfkChannel(@Nonnull GuildUpdateAfkChannelEvent event) {executeClass(GuildUpdateAfkChannelEvent.class, event);}
    @Override public void onGuildUpdateSystemChannel(@Nonnull GuildUpdateSystemChannelEvent event) {executeClass(GuildUpdateSystemChannelEvent.class, event);}
    @Override public void onGuildUpdateRulesChannel(@Nonnull GuildUpdateRulesChannelEvent event) {executeClass(GuildUpdateRulesChannelEvent.class, event);}
    @Override public void onGuildUpdateCommunityUpdatesChannel(@Nonnull GuildUpdateCommunityUpdatesChannelEvent event) {executeClass(GuildUpdateCommunityUpdatesChannelEvent.class, event);}
    @Override public void onGuildUpdateAfkTimeout(@Nonnull GuildUpdateAfkTimeoutEvent event) {executeClass(GuildUpdateAfkTimeoutEvent.class, event);}
    @Override public void onGuildUpdateExplicitContentLevel(@Nonnull GuildUpdateExplicitContentLevelEvent event) {executeClass(GuildUpdateExplicitContentLevelEvent.class, event);}
    @Override public void onGuildUpdateIcon(@Nonnull GuildUpdateIconEvent event) {executeClass(GuildUpdateIconEvent.class, event);}
    @Override public void onGuildUpdateMFALevel(@Nonnull GuildUpdateMFALevelEvent event) {executeClass(GuildUpdateMFALevelEvent.class, event);}
    @Override public void onGuildUpdateName(@Nonnull GuildUpdateNameEvent event){executeClass(GuildUpdateNameEvent.class, event);}
    @Override public void onGuildUpdateNotificationLevel(@Nonnull GuildUpdateNotificationLevelEvent event) {executeClass(GuildUpdateNotificationLevelEvent.class, event);}
    @Override public void onGuildUpdateOwner(@Nonnull GuildUpdateOwnerEvent event) {executeClass(GuildUpdateOwnerEvent.class, event);}

    /**
     * @deprecated This event is no longer supported by discord, use {@link #onVoiceChannelUpdateRegion(VoiceChannelUpdateRegionEvent)} instead.
     */
    @Deprecated
    @ForRemoval(deadline = "5.0.0")
    @ReplaceWith("VoiceChannelUpdateRegionEvent")
    @Override public void onGuildUpdateRegion(@Nonnull GuildUpdateRegionEvent event) {executeClass(GuildUpdateRegionEvent.class, event);}
    @Override public void onGuildUpdateSplash(@Nonnull GuildUpdateSplashEvent event) {executeClass(GuildUpdateSplashEvent.class, event);}
    @Override public void onGuildUpdateVerificationLevel(@Nonnull GuildUpdateVerificationLevelEvent event) {executeClass(GuildUpdateVerificationLevelEvent.class, event);}
    @Override public void onGuildUpdateLocale(@Nonnull GuildUpdateLocaleEvent event) {executeClass(GuildUpdateLocaleEvent.class, event);}
    @Override public void onGuildUpdateFeatures(@Nonnull GuildUpdateFeaturesEvent event) {executeClass(GuildUpdateFeaturesEvent.class, event);}
    @Override public void onGuildUpdateVanityCode(@Nonnull GuildUpdateVanityCodeEvent event) {executeClass(GuildUpdateVanityCodeEvent.class, event);}
    @Override public void onGuildUpdateBanner(@Nonnull GuildUpdateBannerEvent event) {executeClass(GuildUpdateBannerEvent.class, event);}
    @Override public void onGuildUpdateDescription(@Nonnull GuildUpdateDescriptionEvent event) {executeClass(GuildUpdateDescriptionEvent.class, event);}
    @Override public void onGuildUpdateBoostTier(@Nonnull GuildUpdateBoostTierEvent event) {executeClass(GuildUpdateBoostTierEvent.class, event);}
    @Override public void onGuildUpdateBoostCount(@Nonnull GuildUpdateBoostCountEvent event) {executeClass(GuildUpdateBoostCountEvent.class, event);}
    @Override public void onGuildUpdateMaxMembers(@Nonnull GuildUpdateMaxMembersEvent event) {executeClass(GuildUpdateMaxMembersEvent.class, event);}
    @Override public void onGuildUpdateMaxPresences(@Nonnull GuildUpdateMaxPresencesEvent event) {executeClass(GuildUpdateMaxPresencesEvent.class, event);}
    @Override public void onGuildUpdateNSFWLevel(@Nonnull GuildUpdateNSFWLevelEvent event) {executeClass(GuildUpdateNSFWLevelEvent.class, event);}

    //Guild Invite Events
    @Override public void onGuildInviteCreate(@Nonnull GuildInviteCreateEvent event) {executeClass(GuildInviteCreateEvent.class, event);}
    @Override public void onGuildInviteDelete(@Nonnull GuildInviteDeleteEvent event) {executeClass(GuildInviteDeleteEvent.class, event);}

    //Guild Member Events
    @Override public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {executeClass(GuildMemberJoinEvent.class, event);}
    @Override public void onGuildMemberRoleAdd(@Nonnull GuildMemberRoleAddEvent event) {executeClass(GuildMemberRoleAddEvent.class, event);}
    @Override public void onGuildMemberRoleRemove(@Nonnull GuildMemberRoleRemoveEvent event) {executeClass(GuildMemberRoleRemoveEvent.class, event);}

    //Guild Member Update Events
    @Override public void onGuildMemberUpdate(@Nonnull GuildMemberUpdateEvent event) {executeClass(GuildMemberUpdateEvent.class, event);}
    @Override public void onGuildMemberUpdateNickname(@Nonnull GuildMemberUpdateNicknameEvent event) {executeClass(GuildMemberUpdateNicknameEvent.class, event);}
    @Override public void onGuildMemberUpdateAvatar(@Nonnull GuildMemberUpdateAvatarEvent event) {executeClass(GuildMemberUpdateAvatarEvent.class, event);}
    @Override public void onGuildMemberUpdateBoostTime(@Nonnull GuildMemberUpdateBoostTimeEvent event) {executeClass(GuildMemberUpdateBoostTimeEvent.class, event);}
    @Override public void onGuildMemberUpdatePending(@Nonnull GuildMemberUpdatePendingEvent event) {executeClass(GuildMemberUpdatePendingEvent.class, event);}

    //Guild Voice Events
    @Override public void onGuildVoiceUpdate(@Nonnull GuildVoiceUpdateEvent event) {executeClass(GuildVoiceUpdateEvent.class, event);}
    @Override public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {executeClass(GuildVoiceJoinEvent.class, event);}
    @Override public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {executeClass(GuildVoiceMoveEvent.class, event);}
    @Override public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {executeClass(GuildVoiceLeaveEvent.class, event);}
    @Override public void onGuildVoiceMute(@Nonnull GuildVoiceMuteEvent event) {executeClass(GuildVoiceMuteEvent.class, event);}
    @Override public void onGuildVoiceDeafen(@Nonnull GuildVoiceDeafenEvent event) {executeClass(GuildVoiceDeafenEvent.class, event);}
    @Override public void onGuildVoiceGuildMute(@Nonnull GuildVoiceGuildMuteEvent event) {executeClass(GuildVoiceGuildMuteEvent.class, event);}
    @Override public void onGuildVoiceGuildDeafen(@Nonnull GuildVoiceGuildDeafenEvent event) {executeClass(GuildVoiceGuildDeafenEvent.class, event);}
    @Override public void onGuildVoiceSelfMute(@Nonnull GuildVoiceSelfMuteEvent event) {executeClass(GuildVoiceSelfMuteEvent.class, event);}
    @Override public void onGuildVoiceSelfDeafen(@Nonnull GuildVoiceSelfDeafenEvent event) {executeClass(GuildVoiceSelfDeafenEvent.class, event);}
    @Override public void onGuildVoiceSuppress(@Nonnull GuildVoiceSuppressEvent event) {executeClass(GuildVoiceSuppressEvent.class, event);}
    @Override public void onGuildVoiceStream(@Nonnull GuildVoiceStreamEvent event) {executeClass(GuildVoiceStreamEvent.class, event);}
    @Override public void onGuildVoiceVideo(@Nonnull GuildVoiceVideoEvent event) {executeClass(GuildVoiceVideoEvent.class, event);}
    @Override public void onGuildVoiceRequestToSpeak(@Nonnull GuildVoiceRequestToSpeakEvent event) {executeClass(GuildVoiceRequestToSpeakEvent.class, event);}

    //Role events
    @Override public void onRoleCreate(@Nonnull RoleCreateEvent event) {executeClass(RoleCreateEvent.class, event);}
    @Override public void onRoleDelete(@Nonnull RoleDeleteEvent event) {executeClass(RoleDeleteEvent.class, event);}

    //Role Update Events
    @Override public void onRoleUpdateColor(@Nonnull RoleUpdateColorEvent event) {executeClass(RoleUpdateColorEvent.class, event);}
    @Override public void onRoleUpdateHoisted(@Nonnull RoleUpdateHoistedEvent event) {executeClass(RoleUpdateHoistedEvent.class, event);}
    @Override public void onRoleUpdateIcon(@Nonnull RoleUpdateIconEvent event) {executeClass(RoleUpdateIconEvent.class, event);}
    @Override public void onRoleUpdateMentionable(@Nonnull RoleUpdateMentionableEvent event) {executeClass(RoleUpdateMentionableEvent.class, event);}
    @Override public void onRoleUpdateName(@Nonnull RoleUpdateNameEvent event) {executeClass(RoleUpdateNameEvent.class, event);}
    @Override public void onRoleUpdatePermissions(@Nonnull RoleUpdatePermissionsEvent event) {executeClass(RoleUpdatePermissionsEvent.class, event);}
    @Override public void onRoleUpdatePosition(@Nonnull RoleUpdatePositionEvent event) {executeClass(RoleUpdatePositionEvent.class, event);}

    //Emote Events
    @Override public void onEmoteAdded(@Nonnull EmoteAddedEvent event) {executeClass(EmoteAddedEvent.class, event);}
    @Override public void onEmoteRemoved(@Nonnull EmoteRemovedEvent event) {executeClass(EmoteRemovedEvent.class, event);}

    //Emote Update Events
    @Override public void onEmoteUpdateName(@Nonnull EmoteUpdateNameEvent event) {executeClass(EmoteUpdateNameEvent.class, event);}
    @Override public void onEmoteUpdateRoles(@Nonnull EmoteUpdateRolesEvent event) {executeClass(EmoteUpdateRolesEvent.class, event);}

    // Debug Events
    @Override public void onHttpRequest(@Nonnull HttpRequestEvent event) {executeClass(HttpRequestEvent.class, event);}

    //Generic Events
    @Override public void onGenericApplicationCommand(@Nonnull GenericApplicationCommandEvent event) {executeClass(GenericApplicationCommandEvent.class, event);}
    @Override public void onGenericInteractionCreate(@Nonnull GenericInteractionCreateEvent event) {executeClass(GenericInteractionCreateEvent.class, event);}
    @Override public void onGenericComponentInteractionCreate(@Nonnull GenericComponentInteractionCreateEvent event) {executeClass(GenericComponentInteractionCreateEvent.class, event);}
    @Override public void onGenericMessage(@Nonnull GenericMessageEvent event) {executeClass(GenericMessageEvent.class, event);}
    @Override public void onGenericMessageReaction(@Nonnull GenericMessageReactionEvent event) {executeClass(GenericMessageReactionEvent.class, event);}
    @Override public void onGenericGuildMessage(@Nonnull GenericGuildMessageEvent event) {executeClass(GenericGuildMessageEvent.class, event);}
    @Override public void onGenericGuildMessageReaction(@Nonnull GenericGuildMessageReactionEvent event) {executeClass(GenericGuildMessageReactionEvent.class, event);}
    @Override public void onGenericPrivateMessage(@Nonnull GenericPrivateMessageEvent event) {executeClass(GenericPrivateMessageEvent.class, event);}
    @Override public void onGenericPrivateMessageReaction(@Nonnull GenericPrivateMessageReactionEvent event) {executeClass(GenericPrivateMessageReactionEvent.class, event);}
    @Override public void onGenericUser(@Nonnull GenericUserEvent event) {executeClass(GenericUserEvent.class, event);}
    @Override public void onGenericUserPresence(@Nonnull GenericUserPresenceEvent event) {executeClass(GenericUserPresenceEvent.class, event);}
    @Override public void onGenericSelfUpdate(@Nonnull GenericSelfUpdateEvent event) {executeClass(GenericSelfUpdateEvent.class, event);}
    @Override public void onGenericStoreChannel(@Nonnull GenericStoreChannelEvent event) {executeClass(GenericStoreChannelEvent.class, event);}
    @Override public void onGenericStoreChannelUpdate(@Nonnull GenericStoreChannelUpdateEvent event) {executeClass(GenericStoreChannelUpdateEvent.class, event);}
    @Override public void onGenericTextChannel(@Nonnull GenericTextChannelEvent event) {executeClass(GenericTextChannelEvent.class, event);}
    @Override public void onGenericTextChannelUpdate(@Nonnull GenericTextChannelUpdateEvent event) {executeClass(GenericTextChannelUpdateEvent.class, event);}
    @Override public void onGenericVoiceChannel(@Nonnull GenericVoiceChannelEvent event) {executeClass(GenericVoiceChannelEvent.class, event);}
    @Override public void onGenericVoiceChannelUpdate(@Nonnull GenericVoiceChannelUpdateEvent event) {executeClass(GenericVoiceChannelUpdateEvent.class, event);}
    @Override public void onGenericCategory(@Nonnull GenericCategoryEvent event) {executeClass(GenericCategoryEvent.class, event);}
    @Override public void onGenericCategoryUpdate(@Nonnull GenericCategoryUpdateEvent event) {executeClass(GenericCategoryUpdateEvent.class, event);}
    @Override public void onGenericStageInstance(@Nonnull GenericStageInstanceEvent event) {executeClass(GenericStageInstanceEvent.class, event);}
    @Override public void onGenericStageInstanceUpdate(@Nonnull GenericStageInstanceUpdateEvent event) {executeClass(GenericStageInstanceUpdateEvent.class, event);}
    @Override public void onGenericGuild(@Nonnull GenericGuildEvent event) {executeClass(GenericGuildEvent.class, event);}
    @Override public void onGenericGuildUpdate(@Nonnull GenericGuildUpdateEvent event) {executeClass(GenericGuildUpdateEvent.class, event);}
    @Override public void onGenericGuildInvite(@Nonnull GenericGuildInviteEvent event) {executeClass(GenericGuildInviteEvent.class, event);}
    @Override public void onGenericGuildMember(@Nonnull GenericGuildMemberEvent event) {executeClass(GenericGuildMemberEvent.class, event);}
    @Override public void onGenericGuildMemberUpdate(@Nonnull GenericGuildMemberUpdateEvent event) {executeClass(GenericGuildMemberUpdateEvent.class, event);}
    @Override public void onGenericGuildVoice(@Nonnull GenericGuildVoiceEvent event) {executeClass(GenericGuildVoiceEvent.class, event);}
    @Override public void onGenericRole(@Nonnull GenericRoleEvent event) {executeClass(GenericRoleEvent.class, event);}
    @Override public void onGenericRoleUpdate(@Nonnull GenericRoleUpdateEvent event) {executeClass(GenericRoleUpdateEvent.class, event);}
    @Override public void onGenericEmote(@Nonnull GenericEmoteEvent event) {executeClass(GenericEmoteEvent.class, event);}
    @Override public void onGenericEmoteUpdate(@Nonnull GenericEmoteUpdateEvent event) {executeClass(GenericEmoteUpdateEvent.class, event);}
    @Override public void onGenericPermissionOverride(@Nonnull GenericPermissionOverrideEvent event) {executeClass(GenericPermissionOverrideEvent.class, event);}

    public void onMessageConsoleReceived(MessageConsoleReceivedEvent event) {
    	executeClass(MessageConsoleReceivedEvent.class, event);
    }

	
}
